package sp.fr.advancedlayout;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import sm.fr.advancedlayoutapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInscription extends Fragment {

    DrawerActivity parentActivity;
    EditText editTextUser;


    public FragmentInscription() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_inscription, container, false);

        //Récupération d'une référence au champs du formulaire
        editTextUser = view.findViewById(R.id.editTextUser);

        //Récupération de la référence à l'activité
        parentActivity = (DrawerActivity) getActivity();

        //Gestion du clic sur le bouton valider
        Button btValide = view.findViewById(R.id.buttonValide);
        btValide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Récupération de la saisie de l'utilisateur
                String username = editTextUser.getText().toString();
                //Récupération de l'entité utilisateur et modification du nom de l'utilisateur
                parentActivity.getUser().setUsername(username);
                parentActivity.goToFragmentB();
            }
        });

        return view;
    }

}
