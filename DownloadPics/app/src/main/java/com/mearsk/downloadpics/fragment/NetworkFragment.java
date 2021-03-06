package com.mearsk.downloadpics.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mearsk.downloadpics.R;
import com.mearsk.downloadpics.activities.TActivity;
import com.mearsk.downloadpics.receiver.DownloadReceiver;
import com.mearsk.downloadpics.services.DownloadService;


public class NetworkFragment extends Fragment {
    private String mResult;
    private TextInputEditText mUrlLink;
    private TextInputLayout tilUrl;
    private ProgressBar progressBar;
    private Button mDownload;
    private Button mNextAct;
    private Activity mActivity;

    public NetworkFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        if (childFragment.getActivity() instanceof NetworkRequestListener) {
            mListener = (NetworkRequestListener) childFragment.getActivity();
        } else {
            throw new IllegalStateException("Parent activity must implement NetworkRequestListener");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if ((mResult != null) && (mListener != null)) {
            mListener.onRequestFinished(mResult);
            mResult = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_network, container, false);
        mActivity = getActivity();
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mUrlLink = view.findViewById(R.id.etDownload);
        tilUrl = view.findViewById(R.id.tilDownload);
        mDownload = view.findViewById(R.id.download);
        mNextAct = view.findViewById(R.id.playYoutube);
        progressBar = view.findViewById(R.id.progressBar);
        setListeners();
    }

    private void setListeners() {
        mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValid(mUrlLink.getText().toString());
            }
        });
        mNextAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TActivity.class));
            }
        });
    }


    private void isValid(String link) {
        if (link.isEmpty()) {
            tilUrl.setError("Please Enter the URL");
        } else {
            tilUrl.setError(null);
            mListener.onRequestStarted();
            Intent intent = new Intent(getActivity(), DownloadService.class);
            intent.putExtra("url", "https://sample-videos.com/video123/mp4/480/big_buck_bunny_480p_5mb.mp4");
            intent.putExtra("receiver", new DownloadReceiver(new Handler(), mListener));
            getActivity().startService(intent);

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private NetworkRequestListener mListener = new NetworkRequestListener() {

        @Override
        public void onRequestProgressUpdate(int progress) {
            progressBar.setProgress(progress);
        }

        @Override
        public void onRequestFinished(String result) {
            mDownload.setEnabled(true);
            Toast.makeText(mActivity, "Completed Downloading", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestStarted() {
            mUrlLink.setText("");
            mDownload.setEnabled(false);
        }
    };

    public interface NetworkRequestListener {
        void onRequestProgressUpdate(int progress);

        void onRequestFinished(String result);

        void onRequestStarted();
    }

}
