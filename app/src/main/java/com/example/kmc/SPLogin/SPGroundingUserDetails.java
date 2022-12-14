package com.example.kmc.SPLogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kmc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class SPGroundingUserDetails extends AppCompatActivity {


    public TextView individualName;
    public TextView individualFatherName;
    public TextView individualAge;
    public TextView individualHouseNo;
    public TextView individualAadhar;
    public TextView individualPhno;
    public TextView individualVillage;
    public TextView individualMandal;
    public TextView individualDistrict;
    public TextView individualPreferredUnit;
    public TextView individualBankName;
    public TextView individualBankAccNo;
    public TextView individualPSUpload;
    public TextView getIndividualBankIFSC;
    public TextView getIndividualRequestedAmount;
    public TextView getVendorName;
    public TextView getVendorBankName;
    public TextView getVendorBankAccountNo;
    public TextView getVendorBankIFSC;
    public TextView getPSRequestedAmount;
    public TextView individualDBAccount;
    public TextView getVendorAgency;
    public TextView getAmountApproved;
    public TextView getDBAccountAmount;
    public TextView getDbBankName;
    public TextView getDbAccNumber;
    public TextView getDbIFSC;

    private TextInputEditText individualSPRemarks;
    private TextInputEditText spAmountApproved;
    public TextView getIndividualDBAmount;
    public TextView getIndividualApprovalAmount;

    Button approve;
    Button reject;
    String approved;
    FirebaseFirestore db;
    String indivName;
    String fatherName;
    String age;
    String houseNumber;
    String aadharNumber;
    String mobileNumber;
    String preferredunit;
    String bankName;
    String bankACCNumber;
    String spRemarks;
    String collectorApproved;
    String status;
    String village;
    String mandal;
    String district;
    String village1;
    String village2;
    String groundImage;
    Button quotationImageButton;
    String spApprovedAmount;
    String qImage="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spgrounding_status);
        db= FirebaseFirestore.getInstance();
        individualName  = (TextView) findViewById(R.id.IndividualName);
        individualFatherName=(TextView) findViewById(R.id.FatherName);
        individualAge=(TextView) findViewById(R.id.Age);
        individualHouseNo=(TextView) findViewById(R.id.HouseNumber);
        individualVillage=(TextView) findViewById(R.id.village);
        individualMandal=(TextView) findViewById(R.id.mandal);
        individualDistrict=(TextView) findViewById(R.id.district);
        individualAadhar=(TextView) findViewById(R.id.AadharNumber);
        individualPhno=(TextView) findViewById(R.id.MobileNumber);
        individualPreferredUnit=(TextView) findViewById(R.id.Preferredunit);
        individualBankName=(TextView) findViewById(R.id.BankName);
        individualBankAccNo=(TextView) findViewById(R.id.BankACCNumber);
        getIndividualBankIFSC=(TextView) findViewById(R.id.BankIFSC);
        getIndividualRequestedAmount=(TextView) findViewById(R.id.requestedAmount);
        individualPSUpload=(TextView) findViewById(R.id.psUpload);
        getVendorAgency=(TextView) findViewById(R.id.vendorAgency);
        getVendorBankAccountNo=(TextView) findViewById(R.id.vendorBankAccountNo);
        getVendorBankIFSC=(TextView) findViewById(R.id.vendorBankIFSC);
        getVendorName=(TextView) findViewById(R.id.vendorName);
        getVendorBankName=(TextView) findViewById(R.id.vendorBankName);
        individualSPRemarks=(TextInputEditText) findViewById(R.id.remarks);
        spAmountApproved=(TextInputEditText) findViewById(R.id.spAmountApproved);
        getIndividualDBAmount=(TextView) findViewById(R.id.dbAccount);
        getPSRequestedAmount=(TextView) findViewById(R.id.psRequestedAmount);
        getAmountApproved=(TextView) findViewById(R.id.approvedAmount);
        getDbBankName = (TextView) findViewById(R.id.DbBankName);
        getDbAccNumber = (TextView) findViewById(R.id.DbAccNumber);
        getDbIFSC = (TextView) findViewById(R.id.DbBankIFSC);

//        getIndividualDBAmount=(TextView) findViewById(R.id.dbAmount);
        getIndividualApprovalAmount=(TextView) findViewById(R.id.approvalAmount);
        approve=(Button)findViewById(R.id.approve);
        reject=(Button)findViewById(R.id.reject);
        quotationImageButton=(Button)findViewById(R.id.quotationImageButton);
        individualName.setText("Name: "+getIntent().getStringExtra("uname").toString());
        individualFatherName.setText("Father Name: "+getIntent().getStringExtra("ufname").toString());
        individualAge.setText("Age: "+getIntent().getStringExtra("uAge").toString());
        individualHouseNo.setText("House Number: "+getIntent().getStringExtra("uHnumber").toString());
        individualVillage.setText("Village: "+getIntent().getStringExtra("uVillage").toString());
        individualMandal.setText("Mandal: "+getIntent().getStringExtra("uMandal").toString());
        individualDistrict.setText("District: "+getIntent().getStringExtra("uDistrict").toString());
        individualAadhar.setText("Aadhar Number: "+getIntent().getStringExtra("uAadharNumber").toString());
        individualPhno.setText("Mobile Number: "+getIntent().getStringExtra("uMobileNo").toString());
        individualPreferredUnit.setText("Preferred Unit: "+getIntent().getStringExtra("uPreferredUnit").toString());
        individualBankName.setText("Bank Name: "+getIntent().getStringExtra("uBankName").toString());
        individualBankAccNo.setText("Bank Account Number: "+getIntent().getStringExtra("uBankAccNumber").toString());
        getIndividualBankIFSC.setText("Bank IFSC: "+getIntent().getStringExtra("uBankIFSC").toString());
        getVendorName.setText("Vendor Name: "+getIntent().getStringExtra("uVendorName").toString());
        getVendorBankName.setText("Vendor Bank Name: "+getIntent().getStringExtra("uVendorBankName").toString());
        getVendorBankIFSC.setText("Vendor Bank IFSC: "+getIntent().getStringExtra("uVendorBankIFSC").toString());
        getVendorAgency.setText("Vendor Agency: "+getIntent().getStringExtra("uVendorAgency").toString());
        getVendorBankAccountNo.setText("Vendor Bank Account Number: "+getIntent().getStringExtra("uVendorAccountNo").toString());
        getIndividualDBAmount.setText("DB Account: "+getIntent().getStringExtra("uDbAccount").toString());
        getPSRequestedAmount.setText("PS Requested Amount: "+getIntent().getStringExtra("uPSRequestedAmount").toString());
        getAmountApproved.setText("Credited To Vendor: "+getIntent().getStringExtra("uApprovalAmount").toString());//approved amount
        getDbBankName.setText("DB Bank Name: "+getIntent().getStringExtra("uDbBankName").toString());
        getDbAccNumber.setText("DB Account Number: "+getIntent().getStringExtra("uDbAccountNo").toString());
        getDbIFSC.setText("DB Account IFSC: "+getIntent().getStringExtra("uDbIFSC").toString());
        //        getIndividualDBAmount.setText("Dalita Bandhu Account Amount: "+getIntent().getStringExtra("uDbAccount").toString());

        aadharNumber=getIntent().getStringExtra("uAadharNumber").toString();
        village=getIntent().getStringExtra("uVillage").toString();
        mandal= getIntent().getStringExtra("uMandal").toString();
        district=getIntent().getStringExtra("uDistrict").toString();
        village1=getIntent().getStringExtra("village1").toString();
        village2=getIntent().getStringExtra("village2").toString();
        qImage=getIntent().getStringExtra("uGroundingImage").toString();
//        individualSPRemarks.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                enableSubmitIfReady();
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                spRemarks= individualSPRemarks.getText().toString();
//            }
//        });
//
//        collectorApproved=getIntent().getStringExtra("uCollectorApproved").toString();
//        if(collectorApproved.equals("yes"))
//        {
//            approve.setEnabled(false);
//            reject.setEnabled(false);
//            individualSPRemarks.setEnabled(false);
//        }else if(collectorApproved.equals("no"))
//        {
//            approve.setEnabled(false);
//            reject.setEnabled(false);
//            individualSPRemarks.setEnabled(false);
//        }


//        groundImage=getIntent().getStringExtra("uGroundingImage").toString();
//        if(!groundImage.equals(""))
//        {
//            groundImageButton.setEnabled(true);
//        }

    }
    public void groundingImage(View view){
        Uri uri = Uri.parse(qImage); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void enableSubmitIfReady(){
        boolean isReady = individualSPRemarks.getText().toString().length() > 3;
        approve.setEnabled(isReady);
        reject.setEnabled(isReady);
    }

    public void document(View view) {
        String url=getIntent().getStringExtra("psUpload").toString();
        Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }



}