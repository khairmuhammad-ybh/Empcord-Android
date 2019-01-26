package com.khairmuhammad.empcord.fragments.dialogs;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.fragments.officer.account.management.Reset;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordResetDialog extends DialogFragment {

    public interface OnInputSelected{
        void sendInput(String input);
    }

    public OnInputSelected mOnInputSelected;

    EditText dialog_password_reset_new_password_one, dialog_password_reset_new_password_two;
    TextView dialog_password_reset_context, dialog_password_reset_action_cancel, dialog_password_reset_action_ok;

    public PasswordResetDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_password_reset_dialog, container, false);

        dialog_password_reset_context = rootView.findViewById(R.id.dialog_password_reset_context);
        dialog_password_reset_new_password_one = rootView.findViewById(R.id.dialog_password_reset_new_password_one);
        dialog_password_reset_new_password_two = rootView.findViewById(R.id.dialog_password_reset_new_password_two);
        dialog_password_reset_action_cancel = rootView.findViewById(R.id.dialog_password_reset_action_cancel);
        dialog_password_reset_action_ok = rootView.findViewById(R.id.dialog_password_reset_action_ok);

        dialog_password_reset_action_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        dialog_password_reset_action_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passOne = dialog_password_reset_new_password_one.getText().toString();
                String passTwo = dialog_password_reset_new_password_two.getText().toString();

                //check for password match
                if(passOne.equals(passTwo)){
                    mOnInputSelected.sendInput(passOne);
                    getDialog().dismiss();
                }else{

                }
//                Reset frag = (Reset) getActivity().getSupportFragmentManager().findFragmentByTag("Reset");
//                frag.newPassword = input;


            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        }catch (ClassCastException e){
            Log.d("Error", "Class exception error: " + e.getMessage());
        }
    }
}
