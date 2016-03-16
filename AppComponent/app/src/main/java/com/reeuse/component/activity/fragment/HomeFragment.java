package com.reeuse.component.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.reeuse.component.R;


public class HomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        showToast("Fragment:: onAttach(...)");
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showToast("Fragment:: onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        showToast("Fragment:: onCreateView(...)");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showToast("Fragment:: onActivityCreated(...)");
    }

    @Override
    public void onResume() {
        super.onResume();
        showToast("Fragment:: onResume()");
        if (mListener != null) {
            mListener.onFragmentInteraction("HomeFragment");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        showToast("Fragment:: onPause()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        showToast("Fragment:: onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showToast("Fragment:: onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        showToast("Fragment:: onDetach()");
        mListener = null;
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
