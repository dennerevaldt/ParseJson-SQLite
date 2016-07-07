package br.unisinos.tarefa02rr.activitys;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.unisinos.tarefa02rr.R;
import br.unisinos.tarefa02rr.adapters.AdapterUser;
import br.unisinos.tarefa02rr.database.UserTasks;
import br.unisinos.tarefa02rr.models.User;
import br.unisinos.tarefa02rr.web.ConnectionUtil;
import br.unisinos.tarefa02rr.web.UserHttp;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private Exception exception;
    private GetDataAsync mGetDataAsynk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Lista de usuários");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            getData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        if (ConnectionUtil.hasConnection(this)) {
            mGetDataAsynk = new GetDataAsync();
            mGetDataAsynk.execute();
        } else {
            Toast.makeText(this, "Sem conexão", Toast.LENGTH_LONG).show();
        }
    }

    class GetDataAsync extends AsyncTask<Void, Void, ArrayList<User>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Buscando dados...");
            progressDialog.show();
        }

        @Override
        protected ArrayList<User> doInBackground(Void... params) {
            UserHttp userHttp = new UserHttp();
            try {
                ArrayList<User> userList = userHttp.getDataApi();
                return userList;
            } catch (Exception e) {
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<User> userList) {
            super.onPostExecute(userList);
            progressDialog.dismiss();
            if (exception != null) {
                Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                onSuccessRequest(userList);
            }
        }
    }

    private void onSuccessRequest(ArrayList<User> userList) {
        // Salva os dados local
        UserTasks userTasks = new UserTasks();
        // Limpa todos registros
        userTasks.cleanUsers(this);
        // Salva novos registros
        userTasks.saveUsers(userList, this);
        // Busca dados armazenados e atribui ao adapter que mostrará lista na tela
        AdapterUser adapterUser = new AdapterUser(userTasks.getUsers(this), MainActivity.this);
        ListView listView = (ListView) findViewById(R.id.listUsers);
        if (listView != null) {
            listView.setAdapter(adapterUser);
        }
    }
}
