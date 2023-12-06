package com.miwth.allure_spa.ui.views.RateAndReviews;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.slider.Slider;
import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.Comment;
import com.miwth.allure_spa.ui.adapter.CommentRateAndReviewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class Rating_Reviews extends AppCompatActivity {
    private final String TAG = "Rating_Reviews";
    View bottomSheetView;

    ImageButton btnDeletePhoto, btnBack;
    private Slider slider5, slider4, slider3, slider2, slider1;
    private TextView tv5, tv4, tv3, tv2, tv1;
    private RecyclerView rvRatingAndReviews;
    private List<Uri> selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_reviews);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));


        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });


        slider5 = findViewById(R.id.slide5);
        slider4 = findViewById(R.id.slide4);
        slider3 = findViewById(R.id.slide3);
        slider2 = findViewById(R.id.slide2);
        slider1 = findViewById(R.id.slide1);

        tv5 = findViewById(R.id.tv5);
        tv4 = findViewById(R.id.tv4);
        tv3 = findViewById(R.id.tv3);
        tv2 = findViewById(R.id.tv2);
        tv1 = findViewById(R.id.tv1);

        rvRatingAndReviews = findViewById(R.id.rvRatingAndReviews);


        tv5.setText("100");
        tv4.setText("40");
        tv3.setText("21");
        tv2.setText("0");
        tv1.setText("0");

        updateSliderValue(slider5, tv5);
        updateSliderValue(slider4, tv4);
        updateSliderValue(slider3, tv3);
        updateSliderValue(slider2, tv2);
        updateSliderValue(slider1, tv1);

        bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_dialog, null);

        btnDeletePhoto = bottomSheetView.findViewById(R.id.btnDeletePhoto);


        List<Comment> commentList = new ArrayList<>();

        Comment comment1 = new Comment("Nguyễn Văn A", 5, "10/10/2020", "Sản phẩm rất tốt, tôi rất hài lòng về sản phẩm này", true);

        Comment comment2 = new Comment("Nguyễn Văn B", 4, "10/10/2020", "Sản phẩm rất tốt, tôi rất hài lòng về sản phẩm này", true);

        Comment comment3 = new Comment("Nguyễn Văn C", 3, "10/10/2020", "Sản phẩm rất tốt, tôi rất hài lòng về sản phẩm này", true);

        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);

        CommentRateAndReviewsAdapter adapter = new CommentRateAndReviewsAdapter(commentList);
        rvRatingAndReviews.setAdapter(adapter);

        RecyclerView recyclerView = findViewById(R.id.rvRatingAndReviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btnShowDialog = findViewById(R.id.btnShowDialog);
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Rating_Reviews.this);
                bottomSheetDialog.setContentView(bottomSheetView);

                BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());

                bottomSheetBehavior.setPeekHeight(1500);

                bottomSheetDialog.show();

                LinearLayout lnlAddPhoto = bottomSheetView.findViewById(R.id.lnlAddPhoto);

// Limit the number of selected images to 2
                lnlAddPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

                        Toast.makeText(Rating_Reviews.this, "Chọn tối đa 2 hình sản phẩm.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void updateSliderValue(Slider slider, TextView textView) {
        String valueStr = textView.getText().toString();
        float value = Float.parseFloat(valueStr);
        slider.setValue(value);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null && data.getClipData() != null) {
                int count = Math.min(data.getClipData().getItemCount(), 2); // Limit selected images to 2
                selectedImageUri = new ArrayList<>();
                LinearLayout lnlSelectedPhoto = bottomSheetView.findViewById(R.id.lnlSelectedPhoto);
                lnlSelectedPhoto.removeAllViews();

                for (int i = 0; i < count; i++) {
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    selectedImageUri.add(imageUri);
                    ImageView imageView = new ImageView(this);
                    imageView.setImageURI(imageUri);
                    imageView.setPadding(0, 0, 10, 0);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(250, 200));
                    lnlSelectedPhoto.addView(imageView);
                }

                btnDeletePhoto.setVisibility(View.VISIBLE);

                btnDeletePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lnlSelectedPhoto.removeAllViews();
                        selectedImageUri.clear();
                        btnDeletePhoto.setVisibility(View.GONE);
                    }
                });
            }
        }
    }
}
