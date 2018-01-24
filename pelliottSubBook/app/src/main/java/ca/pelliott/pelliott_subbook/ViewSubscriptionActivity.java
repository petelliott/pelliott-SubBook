package ca.pelliott.pelliott_subbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static ca.pelliott.pelliott_subbook.EditSubscriptionActivity.SUBSCRIPTION_EXTRA;

/**
 * gets passed an index to display and provides the option to edit
 */

public class ViewSubscriptionActivity extends AppCompatActivity {
    static final int EDIT_SUBSCRIPTION_REQUEST = 1;

    private Subscription sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subscription);

        Intent intent = getIntent();
        final int index = intent.getIntExtra(SUBSCRIPTION_EXTRA, -1);
        sub = SubscriptionList.getSubscr(index);

        showSubcription(sub);

        FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.editSub);

        fabEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditSubscriptionActivity.class);
                intent.putExtra(SUBSCRIPTION_EXTRA, index);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        showSubcription(sub);
    }

    private void showSubcription(Subscription sub) {
        TextView showName    = (TextView) findViewById(R.id.showName);
        TextView showComment = (TextView) findViewById(R.id.showComment);
        TextView showDate    = (TextView) findViewById(R.id.showDate);
        TextView showPrice   = (TextView) findViewById(R.id.showPrice);

        showName.setText(sub.getName());
        showComment.setText(sub.getComment());
        showDate.setText(sub.getDate().toString());
        showPrice.setText(String.format("$%.2f", sub.getCharge()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // from https://developer.android.com/guide/topics/ui/menus.html
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_sub_menu, menu);
        return true;
    }

    // from https://developer.android.com/guide/topics/ui/menus.html
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                AlertDialog.Builder confirm = new AlertDialog.Builder(this);
                confirm.setMessage("are you sure you want to delete this Subscription?");

                // idea from http://jymden.com/android-simple-confirm-dialog/
                confirm.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        SubscriptionList.remove(sub);
                        finish(); // end the activity so we remove the invalid reference
                    }
                });

                confirm.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                confirm.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}