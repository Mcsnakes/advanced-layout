package sp.fr.advancedlayout;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sm.fr.advancedlayoutapp.R;
import sp.fr.advancedlayout.model.RandomUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class RandomUserFragment extends Fragment implements AdapterView.OnItemClickListener {

    private List<RandomUser> userList;
    private ListView userListView;
    public RandomUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDataFromHttp();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_random_user, container, false);

        userListView = view.findViewById(R.id.randomUserListView);

        userListView.setOnItemClickListener(this);

        return  view;
    }

    private void processResponse(String response) {

        //Transformation de la reponse en liste de RandomUser
        userList = responseToList(response);

        //Conversion de la liste des RandomUser en un tableau de String comportant
        // uniquement le nom de l'utilisateur
        String[] data = new String[userList.size()];
        for(int i = 0 ; i < userList.size(); i++) {
            data[i] = userList.get(i).getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this.getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                data

        );

        userListView.setAdapter(adapter);


    }

    private void getDataFromHttp() {

        String url = "https://jsonplaceholder.typicode.com/users";

        //Définition de la requête
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                //Gestion de succès
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        Log.i("HTTP", response);
                        processResponse(response);

                    }
                },
                //Gestion d'erreur
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("HTTP", error.getMessage() );
                    }
                }

        );

        //Ajout de la requête à la file d'exécution
        Volley  .newRequestQueue(this.getActivity() )
                .add(request);
    }

    /**
     * Conversion d'une réponse JSON (chaine de caractère) en une liste de RandomUsers
     * @param response
     * @return
     */
    private List<RandomUser> responseToList(String response) {

        List<RandomUser> list = new ArrayList<>();

        try {

            JSONArray jsonUsers = new JSONArray(response);
            JSONObject items;
            for(int i = 0; i < jsonUsers.length(); i++) {


                items = (JSONObject) jsonUsers.get(i);

                //Création d'un nouvelle utilisateur
                RandomUser user = new RandomUser();

                //Hydratation de l'utilisateur
                user.setName(items.getString("name"));
                user.setName(items.getString("email"));

                JSONObject geo = items.getJSONObject("address").getJSONObject("geo");
                user.setLatitude(geo.getDouble("lat"));
                user.setLongitude(geo.getDouble("lng"));

                //Ajout de l'utilisateur à la liste
                list.add(user);

            }

        } catch (JSONException ex) {

        }

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        //Récupération de l'utilisateur sur le quel on vient de cliquer
        RandomUser selectedUser = this.userList.get(position);

        //Création d'un intent pour l'affichage de la carte
        Intent mapIntent = new Intent(this.getActivity(), MapsActivity.class);

        //Passage des paramètres à l'intention
        mapIntent.putExtra("latitude", selectedUser.getLatitude() );
        mapIntent.putExtra("longitude", selectedUser.getLongitude() );

        startActivity(mapIntent);

    }
}
