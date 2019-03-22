package sheepyourhack.delf.sheepyourhack_smoczek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewOfferActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditTitleView;
    private EditText mEditDescView;
    private EditText mEditPriceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_offer);
        mEditTitleView = findViewById(R.id.edit_title);
        mEditDescView = findViewById(R.id.edit_desc);
        mEditPriceView = findViewById(R.id.edit_price);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (
                        TextUtils.isEmpty(mEditTitleView.getText()) ||
                        TextUtils.isEmpty(mEditDescView.getText()) ||
                        TextUtils.isEmpty(mEditPriceView.getText())
                ) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String title = mEditTitleView.getText().toString();
                    String desc = mEditDescView.getText().toString();
                    String price = mEditPriceView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, title +'|'+ desc +'|'+ price);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}