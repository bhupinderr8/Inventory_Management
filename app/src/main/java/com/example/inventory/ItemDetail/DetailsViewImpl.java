package com.example.inventory.ItemDetail;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;

import com.example.inventory.DataObject.supplierObject;
import com.example.inventory.DataObject.itemObject;
import com.example.inventory.R;

public class DetailsViewImpl extends AppCompatActivity implements DetailsView {

    private static final String LOG_TAG = DetailsViewImpl.class.getCanonicalName();
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int PICK_IMAGE_REQUEST = 0;
    EditText nameEdit;
    EditText priceEdit;
    EditText quantityEdit;
    String currentItemId;
    EditText itemDescription;
    ImageView imageView;
    Uri actualUri;
    Boolean infoItemHasChanged = false;
    DetailsPresenter presenter;
    SeekBar priceSeekBar;
    SeekBar qtySeekBar;
    TextView supplierTextView;
    Spinner spinner;
    ArrayAdapter<supplierObject> supplierAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        nameEdit = findViewById(R.id.nameEditText);
        priceEdit = findViewById(R.id.priceEditText);
        quantityEdit = findViewById(R.id.qtyEditText);
        imageView = findViewById(R.id.itemImage);
        supplierTextView = findViewById(R.id.supplier_text_view);
        spinner = findViewById(R.id.spinner_supplier_option);
        supplierAdapter = new ArrayAdapter<supplierObject>(this,
                R.layout.drop_down_item);
        spinner.setAdapter(supplierAdapter);
        itemDescription = findViewById(R.id.description);
        currentItemId = getIntent().getExtras().getString("ItemId", "");
        qtySeekBar = findViewById(R.id.qtySeekBar);
        priceSeekBar = findViewById(R.id.priceSeekBar);
        presenter = new DetailsPresenterImpl(this);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToOpenImageSelector();
                presenter.onImageSelectButton();
                infoItemHasChanged = true;
            }
        });

        qtySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                quantityEdit.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        priceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                priceEdit.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!infoItemHasChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // UserItem clicked "Discard" button, close the current activity.
                        finish();
                    }
                };
        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (currentItemId.equals("")) {
            MenuItem deleteOneItemMenuItem = menu.findItem(R.id.action_delete_item);
            MenuItem deleteAllMenuItem = menu.findItem(R.id.action_delete_all_data);
            deleteOneItemMenuItem.setVisible(false);
            deleteAllMenuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                presenter.onActionSave();
                break;
            case android.R.id.home:
                if (!infoItemHasChanged) {
                    NavUtils.navigateUpFromSameTask(this);
                    break;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // UserItem clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(DetailsViewImpl.this);
                            }
                        };
                // Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                break;
            case R.id.action_delete_item:
                // delete one item
                showDeleteConfirmationDialog(currentItemId);
                break;
            case R.id.action_delete_all_data:
                //delete all data
                showDeleteConfirmationDialog("");
                break;
        }
        return true;
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

    @Override
    public String getCurrentItemId() {
        return currentItemId;
    }

    @Override
    public void setTitle(String str) {

    }

    @Override
    public boolean getInfoHasChanged() {
        return infoItemHasChanged;
    }

    @Override
    public void setInfoHasChanged(boolean val) {
        infoItemHasChanged = val;
    }

    @Override
    public String getQuantity() {
        return quantityEdit.getText().toString();
    }

    @Override
    public void setQuantity(String value) {
        quantityEdit.setText(value);
    }

    public void updateValues(itemObject item) {
        nameEdit.setText(item.getItemName());
        priceEdit.setText(String.valueOf(item.getPrice()));
        quantityEdit.setText(String.valueOf(item.getQty()));
        imageView.setImageURI(Uri.parse(item.getImage()));
        nameEdit.setEnabled(false);
        priceEdit.setEnabled(false);
        imageView.setEnabled(false);
    }

    private void showDeleteConfirmationDialog(final String itemId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_message);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (itemId == "") {
                    presenter.deleteAllRowsFromTable();
                } else {
                    presenter.deleteOneItemFromTable(itemId);
                }
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void tryToOpenImageSelector() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            return;
        }
    }

    @Override
    public void openImageSelector() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void finishActtivity() {
        finish();
    }

    @Override
    public boolean addItemToDb() {
        boolean isAllOk = true;
        if (!checkIfValueSet(nameEdit, "name")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(priceEdit, "price")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(quantityEdit, "quantity")) {
            isAllOk = false;
        }
        if (actualUri == null && currentItemId.equals("")) {
            isAllOk = false;
            imageView.setImageResource(R.drawable.common_google_signin_btn_icon_dark);
        }
        return isAllOk;
    }

    @Override
    public String getCurrentName() {
        return nameEdit.getText().toString().trim();
    }

    @Override
    public String getImage() {
        return actualUri.toString();
    }

    @Override
    public void setImage(String image) {
        imageView.setImageURI(Uri.parse(image));
    }

    @Override
    public String getItemDescription() {
        return itemDescription.getText().toString();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    public String getPrice() {
        return priceEdit.getText().toString().trim();
    }

    @Override
    public void setPrice(String valueOf) {
        priceEdit.setText(valueOf);
    }

    @Override
    public void setName(String itemName) {
        nameEdit.setText(itemName);
    }

    @Override
    public void setNameEnable(boolean b) {
        nameEdit.setEnabled(b);
    }

    @Override
    public void setPriceEnable(boolean b) {
        priceEdit.setEnabled(b);
    }

    @Override
    public void setImageEnable(boolean b) {
        imageView.setEnabled(b);
    }

    @Override
    public void addSupplier(supplierObject item) {
        supplierAdapter.add(item);
        supplierAdapter.notifyDataSetChanged();
    }

    @Override
    public String getCurrentSellerId() {
        int pos = spinner.getSelectedItemPosition();
        return supplierAdapter.getItem(pos).getSupplierId();
    }

    @Override
    public void setSpinnerEnable(boolean b) {
        spinner.setEnabled(b);
    }

    @Override
    public void setPriceSeekBarEnable(boolean b) {
        priceSeekBar.setEnabled(b);
    }

    @Override
    public void setDescription(String description) {
        itemDescription.setText(description);
    }

    @Override
    public void setDescriptionEnable(boolean b) {
        itemDescription.setEnabled(b);
    }

    @Override
    public void setSupplierName(String name) {
        supplierTextView.setText(name);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openImageSelector();
                    // permission was granted
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        // The ACTION_OPEN_DOCUMENT intent was sent with the request code READ_REQUEST_CODE.
        // If the request code seen here doesn't match, it's the response to some other intent,
        // and the below code shouldn't run at all.

        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.  Pull that uri using "resultData.getData()"

            if (resultData != null) {
                actualUri = resultData.getData();
                imageView.setImageURI(actualUri);
                imageView.invalidate();
            }
        }
    }
}
