package com.example.menudialogtest;


import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ViewGroup frame = new FrameLayout(this);
        frame.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        final TextView button = new Button(this);
        button.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        button.setText("Show Dialog");
        button.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final DialogFragment dialogFragment = new MyDialogFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(dialogFragment, "tag");
                transaction.commit();

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        dialogFragment.getDialog().hide();

                        button.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Dialog hidden. HOME and restore to show.", Toast.LENGTH_LONG).show();
                    }
                }, 0);
            }
        });

        frame.addView(button);
        setContentView(frame);
    }


    public static class MyDialogFragment extends DialogFragment
    {
        @Override
        public Dialog onCreateDialog(Bundle state)
        {
            Builder builder = new Builder(getActivity());
            builder.setTitle("Dialog Fragment");
            builder.setMessage("Dialog message");

            return builder.create();
        }
    }
}
