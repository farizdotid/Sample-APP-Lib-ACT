package meridianid.farizdotid.sampleappact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import meridianid.farizdotid.actdaerahindonesia.adapter.SuggestionDesaAdapter;
import meridianid.farizdotid.actdaerahindonesia.adapter.SuggestionKabAdapter;
import meridianid.farizdotid.actdaerahindonesia.adapter.SuggestionKecAdapter;
import meridianid.farizdotid.actdaerahindonesia.adapter.SuggestionProvAdapter;
import meridianid.farizdotid.actdaerahindonesia.util.JsonParse;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView acttext_prov, acttext_kab, acttext_kec, acttext_desa;
    private Button button_show;
    private JsonParse jsonParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonParse = new JsonParse(this);
        initComponents();
        initAdapterACT();
    }

    private void initComponents(){
        acttext_prov = (AutoCompleteTextView) findViewById(R.id.acttext_prov);
        acttext_kab = (AutoCompleteTextView) findViewById(R.id.acttext_kab);
        acttext_kec = (AutoCompleteTextView) findViewById(R.id.acttext_kec);
        acttext_desa = (AutoCompleteTextView) findViewById(R.id.acttext_desa);

        button_show = (Button) findViewById(R.id.button_show);
        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResults();
            }
        });
    }

    private void initAdapterACT(){
        acttext_prov.setAdapter(new SuggestionProvAdapter(this, acttext_prov.getText().toString()));
        acttext_prov.setThreshold(1);
        acttext_prov.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String namaProv = parent.getItemAtPosition(position).toString();
                jsonParse.searchIdProv(namaProv);
            }
        });

        acttext_kab.setAdapter(new SuggestionKabAdapter(this, acttext_prov.getText().toString(),
                acttext_kab.getText().toString()));
        acttext_kab.setThreshold(1);
        acttext_kab.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String namaKab = parent.getItemAtPosition(position).toString();
                jsonParse.searchIdKab(namaKab);
            }
        });

        acttext_kec.setAdapter(new SuggestionKecAdapter(this, acttext_kab.getText().toString(),
                acttext_kec.getText().toString()));
        acttext_kec.setThreshold(1);
        acttext_kec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String namaKec = parent.getItemAtPosition(position).toString();
                jsonParse.searchIdKec(namaKec);
            }
        });

        acttext_desa.setAdapter(new SuggestionDesaAdapter(this, acttext_kec.getText().toString(),
                acttext_desa.getText().toString()));
        acttext_desa.setThreshold(1);
        acttext_desa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void showResults(){
        String provinsi = acttext_prov.getText().toString();
        String kabupaten = acttext_kab.getText().toString();
        String kecamatan = acttext_kec.getText().toString();
        String desa = acttext_desa.getText().toString();

        Toast.makeText(this, "Provinsi : " + provinsi + "\n" +
                "Kabupaten : " + kabupaten + "\n" +
                "Kecamatan : " + kecamatan + "\n" +
                "Desa : " + desa, Toast.LENGTH_SHORT).show();
    }
}
