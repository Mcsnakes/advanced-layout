package sp.fr.advancedlayout;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import sm.fr.advancedlayoutapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Ajout d'un fragment à l'écran
     * @param view
     */
    public void onAddFragment(View view) {
        //Instanciation du fragment
        FragmentB fragmentB = new FragmentB();
        //Récupération d'une instance du gestionnaire de fragment
        FragmentManager manager = getFragmentManager();
        //Début de la transaction
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentContainer, fragmentB);

        transaction.commit();
    }

    public void onReplaceFragment(View view) {
       getFragmentManager()
               .beginTransaction()
               .replace(R.id.fragment1, new FragmentB())
               .commit();
    }
}
