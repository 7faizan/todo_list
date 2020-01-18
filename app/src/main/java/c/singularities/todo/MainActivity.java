package c.singularities.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userInput;
    TextView records;
    Button addButton,deleteButton;
    MyDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput=findViewById(R.id.userinput);
        records=findViewById(R.id.records);
        addButton=findViewById(R.id.add);
        deleteButton=findViewById(R.id.delete);
        dbHandler=new MyDBHandler(this,null,null,1);
        printDatabase();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Products product=new Products(userInput.getText().toString());
                String val=product.get_productname();
                Toast.makeText(MainActivity.this,val,Toast.LENGTH_LONG);
                dbHandler.addProduct(product);
                printDatabase();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
               String inputText= userInput.getText().toString();
               dbHandler.deleteProduct(inputText);
               printDatabase();
            }
        });


    }
    public void printDatabase()
    {
        String dbString=dbHandler.databaseToString();

        records.setText(dbString);
        userInput.setText("");
    }
    /*public void addButtonClicked(View view){
        Products products=new Products(userInput.getText().toString());
        dbHandler.addProduct(products);
        printDatabase();
    }
    public void deleteButtonClicked(View view){
            String inputText=userInput.getText().toString();
            dbHandler.deleteProduct(inputText);
            printDatabase();
    }*/

}
