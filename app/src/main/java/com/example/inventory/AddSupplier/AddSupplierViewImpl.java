package com.example.inventory.AddSupplier;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inventory.R;

public class AddSupplierViewImpl extends AppCompatActivity implements AddSupplierView {
    EditText supplierNameEditText, supplierEmailEditText, supplierPhoneNumberEditText, supplierDescriptionEditText;
    AddSupplierPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_details);
        supplierNameEditText = findViewById(R.id.supplier_name);
        supplierEmailEditText = findViewById(R.id.supplier_email_address);
        supplierPhoneNumberEditText = findViewById(R.id.supplier_phone_number);
        supplierDescriptionEditText = findViewById(R.id.supplier_details);

        presenter = new AddSupplierPresenterImpl(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_supplier_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save_supplier:
                presenter.OnAddSupplierButton();
                break;
        }
        return true;
    }

    @Override
    public boolean checkValuesSet() {
        boolean isAllOk = true;
        if (!checkIfValueSet(supplierNameEditText, "Name")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(supplierEmailEditText, "Email")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(supplierPhoneNumberEditText, "Phone")) {
            isAllOk = false;
        }
        return isAllOk;
    }

    @Override
    public String getSupplierEmail() {
        return supplierEmailEditText.getText().toString();
    }

    @Override
    public String getSupplierPhone() {
        return supplierNameEditText.getText().toString();
    }

    @Override
    public String getSupplierDescription() {
        return supplierDescriptionEditText.getText().toString();
    }

    @Override
    public String getSupplierName() {
        return supplierNameEditText.getText().toString();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void show(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    private boolean checkIfValueSet(EditText text, String description) {
        if (TextUtils.isEmpty(text.getText())) {
            text.setError("Missing product " + description);
            return false;
        } else {
            text.setError(null);
            return true;
        }
    }
}
