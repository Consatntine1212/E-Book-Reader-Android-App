package com.unipi.conchro;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // creating a variable for our list view,
    // arraylist and firebase Firestore.
    ListView coursesLV;
    ArrayList<DataModal> dataModalArrayList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // below line is use to initialize our variables
        coursesLV = findViewById(R.id.idLVCourses);
        // For ListView
        /*
        coursesLV.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int lastVisibleItem = firstVisibleItem + visibleItemCount;

                if (lastVisibleItem == totalItemCount) {
                    // Load more ebooks when the user has scrolled to the end of the list
                    // Fetch the next batch of ebooks from Firestore and add them to your list or adapter
                }
            }
        });*/
        dataModalArrayList = new ArrayList<>();

        // initializing our variable for firebase
        // firestore and getting its instance.
        db = FirebaseFirestore.getInstance();

        // here we are calling a method
        // to load data in our list view.
        loadDatainListview();


    }
    private void loadDatainListview() {
        // Create a query to get data from Firestore with a limit of 10 documents
        // Note: You can adjust the limit to any desired value
        db.collection("Books")
                .limit(20)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are hiding
                            // our progress bar and adding our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.
                                DataModal dataModal = d.toObject(DataModal.class);

                                // after getting data from Firebase we are
                                // storing that data in our array list
                                dataModalArrayList.add(dataModal);
                            }
                            // after that we are passing our array list to our adapter class.
                            CoursesLVAdapter adapter = new CoursesLVAdapter(MainActivity.this, dataModalArrayList);

                            // after passing this array list to our adapter
                            // class we are setting our adapter to our list view.
                            coursesLV.setAdapter(adapter);

                            // Set up a scroll listener to load more data as the user scrolls
                            // Set up a scroll listener to load more data as the user scrolls
                            coursesLV.setOnScrollListener(new AbsListView.OnScrollListener() {
                                @Override
                                public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                                    // Do nothing
                                }

                                // Declare a boolean flag to track if data is currently being loaded
                                private boolean isLoadingData = false;

                                // Update the onScroll method
                                @Override
                                public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                                    // Check if the user has reached the end of the list and not currently loading data
                                    if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && !isLoadingData) {
                                        // Set the flag to indicate that data is being loaded
                                        isLoadingData = true;

                                        // Load more data by getting the last visible document
                                        DocumentSnapshot lastVisible = queryDocumentSnapshots.getDocuments().get(queryDocumentSnapshots.size() - 1);
                                        db.collection("Books")
                                                .startAfter(lastVisible)
                                                .limit(20) // Adjust the limit to any desired value
                                                .get()
                                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                        // Add the newly loaded data to the list
                                                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                        for (DocumentSnapshot d : list) {
                                                            DataModal dataModal = d.toObject(DataModal.class);
                                                            dataModalArrayList.add(dataModal);
                                                        }
                                                        // Notify the adapter that data has changed
                                                        adapter.notifyDataSetChanged();

                                                        // Set the flag to indicate that data loading is complete
                                                        isLoadingData = false;
                                                    }
                                                });
                                    }
                                }
                            });

                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(MainActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // we are displaying a toast message
                        // when we get any error from Firebase.
                        Toast.makeText(MainActivity.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}