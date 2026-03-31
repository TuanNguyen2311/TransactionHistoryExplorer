package com.android.transactionhistoryexplorer.presentation.view.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.transactionhistoryexplorer.R;
import com.android.transactionhistoryexplorer.domain.model.TransactionDetail;
import com.android.transactionhistoryexplorer.domain.model.TransactionSummary;
import com.android.transactionhistoryexplorer.presentation.Resource;
import com.android.transactionhistoryexplorer.presentation.adapter.TransactionAdapter;
import com.android.transactionhistoryexplorer.presentation.view.dialog.TransactionBottomSheet;
import com.android.transactionhistoryexplorer.presentation.viewmodel.TransactionViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    TransactionViewModel transactionViewModel;
    private TransactionAdapter transactionAdapter;
    private RecyclerView rvTransactionList;
    private EditText etSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        setActions();
        initAdapter();
        initViewModel();
        loadData();
    }

    private void setActions() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                transactionViewModel.searchTransactionByNote(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
    }

    private void initViews() {
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvTransactionList = findViewById(R.id.rvTransactionList);
        etSearch = findViewById(R.id.etSearch);
    }

    void initAdapter(){
        transactionAdapter = new TransactionAdapter();
        rvTransactionList.setLayoutManager(new LinearLayoutManager(this));
        rvTransactionList.setAdapter(transactionAdapter);
        transactionAdapter.setListener(new TransactionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int transactionId) {
                transactionViewModel.selectTransaction(transactionId);
            }
        });
    }

    private void loadData() {
        transactionViewModel.loadData();
    }

    private void initViewModel() {
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.getTransactionSummaryPagingData().observe(this, new Observer<PagingData<TransactionSummary>>() {
            @Override
            public void onChanged(PagingData<TransactionSummary> transactionSummaryPagingData) {
                transactionAdapter.submitData(getLifecycle(), transactionSummaryPagingData);
            }
        });
        transactionViewModel.getTransactionCount().observe(this, new Observer<Resource<Integer>>() {
            @Override
            public void onChanged(Resource<Integer> integerResource) {
                if(integerResource==null) return;
                Resource.Status status = integerResource.status;
                if(status==Resource.Status.SUCCESS) {
                    Log.d("MainActivity", "onChanged SUCCESS: " + integerResource.data);
                } else if(status==Resource.Status.ERROR) {
                    Log.d("MainActivity", "onChanged ERROR: " + integerResource.message);
                } else if(status==Resource.Status.LOADING) {
                    Log.d("MainActivity", "onChanged: " + "Loading");
                }
            }
        });

        transactionViewModel.getTransactionDetail().observe(this, new Observer<Resource<TransactionDetail>>() {
            @Override
            public void onChanged(Resource<TransactionDetail> transactionDetailResource) {
                if(transactionDetailResource==null) return;
                Resource.Status status = transactionDetailResource.status;
                if(status==Resource.Status.SUCCESS) {
                    TransactionBottomSheet transactionBottomSheet = TransactionBottomSheet.newInstance(transactionDetailResource.data);
                    transactionBottomSheet.show(getSupportFragmentManager(), "TransactionBottomSheet");
                    Log.d("MainActivity", "onChanged SUCCESS: " + transactionDetailResource.data);
                } else if(status==Resource.Status.ERROR) {
                    Log.d("MainActivity", "onChanged ERROR: " + transactionDetailResource.message);
                } else if(status==Resource.Status.LOADING) {
                    Log.d("MainActivity", "onChanged: " + "Loading");
                }
            }
        });

    }
}