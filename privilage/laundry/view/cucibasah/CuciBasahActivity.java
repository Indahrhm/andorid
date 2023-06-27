package com.privilage.laundry.view.cucibasah;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.privilage.laundry.R;
import com.privilage.laundry.utils.FunctionHelper;
import com.privilage.laundry.view.main.About;
import com.privilage.laundry.view.main.Payment;
import com.privilage.laundry.view.main.Setting;
import com.privilage.laundry.viewmodel.AddDataViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import im.delight.android.location.SimpleLocation;

public class CuciBasahActivity extends AppCompatActivity {

    public static final String DATA_TITLE = "TITLE";
    int hargaCassual = 25000, hargaFlat = 15000, hargaKids = 15000, hargaSneakers = 25000, hargaSlipper = 10000;
    int itemCount1 = 0, itemCount2 = 0, itemCount3 = 0, itemCount4 = 0, itemCount5 = 0;
    int countCassual, countFlat, countKids, countSneakers, countSlipper, totalItems, totalPrice;
    String strTitle, strCurrentLocation, strCurrentLatLong;
    double strCurrentLatitude;
    double strCurrentLongitude;
    SimpleLocation simpleLocation;
    AddDataViewModel addDataViewModel;
    Button btnCheckout;
    ImageView imageAdd1, imageAdd2, imageAdd3, imageAdd4, imageAdd5,
            imageMinus1, imageMinus2, imageMinus3, imageMinus4, imageMinus5;
    TextView tvTitle, tvInfo, tvJumlahBarang, tvTotalPrice, tvCassual, tvFlat, tvKids, tvSneakers, tvSlipper,
            tvPriceCassual, tvPriceFlat, tvPriceKids, tvPriceSneakers, tvPriceSlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);

        setLocation();
        setStatusbar();
        setInitLayout();
        setDataCassual();
        setDataFlat();
        setDataKids();
        setDataSneakers();
        setDataSlipper();
        setInputData();
        getCurrentLocation();
    }

    private void setLocation() {
        simpleLocation = new SimpleLocation(this);

        if (!simpleLocation.hasLocationEnabled()) {
            SimpleLocation.openSettings(this);
        }

        //get location
        strCurrentLatitude = simpleLocation.getLatitude();
        strCurrentLongitude = simpleLocation.getLongitude();

        //set location lat long
        strCurrentLatLong = strCurrentLatitude + "," + strCurrentLongitude;
    }

    private void setStatusbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setInitLayout() {
        tvTitle = findViewById(R.id.tvTitle);
        tvInfo = findViewById(R.id.tvInfo);

        tvJumlahBarang = findViewById(R.id.tvJumlahBarang);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        tvCassual = findViewById(R.id.tvCassual);
        tvFlat = findViewById(R.id.tvFlat);
        tvKids = findViewById(R.id.tvKids);
        tvSneakers = findViewById(R.id.tvSneakers);
        tvSlipper = findViewById(R.id.tvSlipper);
        tvPriceCassual = findViewById(R.id.tvPriceCassual);
        tvPriceFlat = findViewById(R.id.tvPriceFlat);
        tvPriceKids = findViewById(R.id.tvPriceKids);
        tvPriceSneakers = findViewById(R.id.tvPriceSneakers);
        tvPriceSlipper = findViewById(R.id.tvPriceSlipper);

        imageAdd1 = findViewById(R.id.imageAdd1);
        imageAdd2 = findViewById(R.id.imageAdd2);
        imageAdd3 = findViewById(R.id.imageAdd3);
        imageAdd4 = findViewById(R.id.imageAdd4);
        imageAdd5 = findViewById(R.id.imageAdd5);
        imageMinus1 = findViewById(R.id.imageMinus1);
        imageMinus2 = findViewById(R.id.imageMinus2);
        imageMinus3 = findViewById(R.id.imageMinus3);
        imageMinus4 = findViewById(R.id.imageMinus4);
        imageMinus5 = findViewById(R.id.imageMinus5);

        btnCheckout = findViewById(R.id.btnCheckout);

        strTitle = getIntent().getExtras().getString(DATA_TITLE);
        if (strTitle != null) {
            tvTitle.setText(strTitle);
        }

        addDataViewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(this.getApplication()))
                .get(AddDataViewModel.class);

        tvJumlahBarang.setText("0 items");
        tvTotalPrice.setText("Rp 0");
        tvInfo.setText("Cuci luar merupakan layanan cuci sepatu bagian luar saja.");

        btnCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(CuciBasahActivity.this, Payment.class);
            startActivity(intent);
        });

    }

    private void setDataCassual() {
        tvCassual.setText(FunctionHelper.rupiahFormat(hargaCassual));
        imageAdd1.setOnClickListener(v -> {
            itemCount1 = itemCount1 + 1;
            tvPriceCassual.setText(itemCount1);
            countCassual = hargaCassual * itemCount1;
            setTotalPrice();
        });

        imageMinus1.setOnClickListener(v -> {
            if (itemCount1 > 0) {
                itemCount1 = itemCount1 - 1;
                tvPriceCassual.setText(itemCount1);
            }
            countCassual = hargaCassual * itemCount1;
            setTotalPrice();
        });
    }

    private void setDataFlat() {
        tvFlat.setText(FunctionHelper.rupiahFormat(hargaFlat));
        imageAdd2.setOnClickListener(v -> {
            itemCount2 = itemCount2 + 1;
            tvPriceFlat.setText(itemCount2);
            countFlat = hargaFlat * itemCount2;
            setTotalPrice();
        });

        imageMinus2.setOnClickListener(v -> {
            if (itemCount2 > 0) {
                itemCount2 = itemCount2 - 1;
                tvPriceFlat.setText(itemCount2);
            }
            countFlat = hargaFlat * itemCount2;
            setTotalPrice();
        });
    }

    private void setDataKids() {
        tvKids.setText(FunctionHelper.rupiahFormat(hargaKids));
        imageAdd3.setOnClickListener(v -> {
            itemCount3 = itemCount3 + 1;
            tvPriceKids.setText(itemCount3);
            countKids = hargaKids * itemCount3;
            setTotalPrice();
        });

        imageMinus3.setOnClickListener(v -> {
            if (itemCount3 > 0) {
                itemCount3 = itemCount3 - 1;
                tvPriceKids.setText(itemCount3);
            }
            countKids= hargaKids * itemCount3;
            setTotalPrice();
        });
    }

    private void setDataSneakers() {
        tvSneakers.setText(FunctionHelper.rupiahFormat(hargaSneakers));
        imageAdd4.setOnClickListener(v -> {
            itemCount4 = itemCount4 + 1;
            tvPriceSneakers.setText(itemCount4);
            countSneakers = hargaSneakers * itemCount4;
            setTotalPrice();
        });

        imageMinus4.setOnClickListener(v -> {
            if (itemCount4 > 0) {
                itemCount4 = itemCount4 - 1;
                tvPriceSneakers.setText(itemCount4);
            }
            countSneakers = hargaSneakers * itemCount4;
            setTotalPrice();
        });
    }

    private void setDataSlipper() {
        tvSlipper.setText(FunctionHelper.rupiahFormat(hargaSlipper));
        imageAdd5.setOnClickListener(v -> {
            itemCount5 = itemCount5 + 1;
            tvPriceSlipper.setText(itemCount5);
            countSlipper = hargaSlipper * itemCount5;
            setTotalPrice();
        });

        imageMinus5.setOnClickListener(v -> {
            if (itemCount5 > 0) {
                itemCount5 = itemCount5 - 1;
                tvPriceSlipper.setText(itemCount5);
            }
            countSlipper = hargaSlipper * itemCount5;
            setTotalPrice();
        });
    }

    private void setTotalPrice() {
        totalItems = itemCount1 + itemCount2 + itemCount3 + itemCount4 + itemCount5;
        totalPrice = countCassual + countFlat+ countKids + countSneakers + countSlipper;

        tvJumlahBarang.setText(totalItems + " items");
        tvTotalPrice.setText(FunctionHelper.rupiahFormat(totalPrice));
    }

    private void getCurrentLocation() {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(strCurrentLatitude, strCurrentLongitude, 1);
            if (addressList != null && addressList.size() > 0) {
                strCurrentLocation = addressList.get(0).getLocality();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setInputData() {
        btnCheckout.setOnClickListener(v -> {
            if (totalItems == 0 || totalPrice == 0) {
                Toast.makeText(CuciBasahActivity.this, "Harap pilih jenis barang!", Toast.LENGTH_SHORT).show();
           } else {
                addDataViewModel.addDataLaundry(strTitle, totalItems, strCurrentLocation, totalPrice);
                Toast.makeText(CuciBasahActivity.this, "Pesanan Anda sedang diproses, harap lakukan pembayaran", Toast.LENGTH_SHORT).show();
                finish();
            }
            Intent intent = new Intent(CuciBasahActivity.this, Payment.class);
            startActivity(intent);
        });
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }

}