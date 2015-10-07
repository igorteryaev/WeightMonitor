package igor_teryaev.weightmonitor;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.databinding.DataBindingUtil;
import android.widget.Toast;

import igor_teryaev.weightmonitor.databinding.PersonalDataBinding;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//implements View.OnClickListener, TextView.OnEditorActionListener

public class EditPersonalData extends DialogFragment implements View.OnClickListener {


    private Button mBtnOk;
    private Button mBtnCancel;
    private View mView;
    private UserData mUser;

    public interface DialogCommitListener {
        void onFinishUserDialog(UserData userData);
    }

 /*   public interface UserNameListener {
        void onEnterUserName(String user);
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        mView = inflater.inflate(R.layout.personal_data, container);
        EditText mEditText = (EditText) mView.findViewById(R.id.txt_username);
        RadioGroup mRadioGroup = (RadioGroup)mView.findViewById(R.id.radiogroup);

        mBtnOk = (Button)mView.findViewById(R.id.btn_ok);
        mBtnCancel = (Button)mView.findViewById(R.id.btn_cancel);


        PersonalDataBinding binding = DataBindingUtil.bind(mView);


        mUser = new UserData(0, "Enter your Username", new Date(), true);
        binding.setUser(mUser);

        mBtnOk.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId != R.id.radio_male & mUser.isSexIsMale())
                    mUser.setSexIsMale(false);

                else if(checkedId == R.id.radio_male & !mUser.isSexIsMale())
                    mUser.setSexIsMale(true);

                }
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!mUser.getUserName().equals(s.toString()))
                    mUser.setUserName(s.toString());
            }
        });
     //   mEditText.setOnEditorActionListener(this);
      //  mEditText.requestFocus();
       // getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle("Please enter username");

        return mView;
    }


    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.btn_ok) {
            Toast.makeText(getActivity(), mUser.getUserName(), Toast.LENGTH_LONG).show();
            DialogCommitListener activity = (DialogCommitListener) getActivity();
            activity.onFinishUserDialog(mUser);
        }
        this.dismiss();
    }
/*
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (event != null && event.getAction() != KeyEvent.ACTION_DOWN) {
            return false;
        } else if (actionId == EditorInfo.IME_ACTION_NEXT
                || event == null
                || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

            UserNameListener activity = (UserNameListener) getActivity();
            activity.onEnterUserName(mEditText.getText().toString());


            InputMethodManager inputManager = (InputMethodManager) mView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


        }



       // View radio = mView.findViewById(R.id.radio_male);

       // radio.requestFocus();
        //this.dismiss();
        return true;
    }  */
}