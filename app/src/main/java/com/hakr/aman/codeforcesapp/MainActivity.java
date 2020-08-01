package com.hakr.aman.codeforcesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import android.app.ActionBar;
//import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.aman.codeforcesapp.model.Post;
import com.hakr.aman.codeforcesapp.Adapter.MyAdapter;
import com.hakr.aman.codeforcesapp.Adapter.Tagadapter;
import com.hakr.aman.codeforcesapp.model.Author;
import com.hakr.aman.codeforcesapp.model.Problem;
import com.hakr.aman.codeforcesapp.model.Question;
import com.hakr.aman.codeforcesapp.model.Result;
import com.hakr.aman.codeforcesapp.model.Result1;
import com.hakr.aman.codeforcesapp.model.userinfo;
import com.hakr.aman.codeforcesapp.model.usersubmissioninfo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
//import com.example.aman.codeforcesapp.model.firstTry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
      private TextView organization, lastOnlineTimeSeconds,rating,rank,submissionsextra,handle,name,tagstext,barcharttext,submissions,heading;
      private MyAdapter myAdapter;
      private List<userinfo> plist;
      private List<Author> authorinfolist;
      private List<Problem> probleminfolist;
      private Result r;
      private RecyclerView rv;
      private PieChart piechart;
      private RecyclerView tagsrv;private CardView tagcard;

    @Override
    protected void onStart() {
        super.onStart();
       /* ProgressBar progressBar;

        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.pb);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                progressBar.setVisibility(View.GONE);
            }

        }, 3000);*/
    }

    @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   //     int[] colorarray=new int
        Window window = MainActivity.this.getWindow();
        LinearLayoutManager manager=new LinearLayoutManager(this);
        LinearLayoutManager manager1=new LinearLayoutManager(this);
        tagsrv=(RecyclerView) findViewById(R.id.tagsrv);
        tagstext=findViewById(R.id.tagtext);
        barcharttext=findViewById(R.id.barcharttext);
        submissions=findViewById(R.id.submissions);
        submissionsextra=findViewById(R.id.submissionsextra);
        heading=findViewById(R.id.heading);
        tagcard=findViewById(R.id.tagcard);
        tagsrv.setLayoutManager(manager);
        tagsrv.setNestedScrollingEnabled(false);
        String category=getIntent().getStringExtra("category");

        MyWebService myWebService=MyWebService.retrofit.create(MyWebService.class);
        // Call<List<Result>> call=myWebService.getUserInfo("hakr_2104");
        String user=getIntent().getStringExtra("user");
        Call<Result> call=myWebService.getUserInfo1(user);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response.isSuccessful()){
               //     Toast.makeText(MainActivity.this,"AAlOO le LOOO..."+response.body().getResult().size(),Toast.LENGTH_LONG).show();
                    rv=(RecyclerView) findViewById(R.id.rv);
                    rv.setNestedScrollingEnabled(false);
                    plist=new ArrayList<>();
                    rv.setLayoutManager(manager1);
                    plist =response.body().getResult();
                    ActionBar ab=getSupportActionBar();
                    if(plist.get(0).getRank()!=null){
                    if(plist.get(0).getRank().toLowerCase().equals("expert")){
                         ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3f51b5")));
                         window.setStatusBarColor(Color.parseColor("#002984"));
                         tagcard.setCardBackgroundColor(Color.parseColor("#3f51b5"));
                         tagstext.setTextColor(Color.parseColor("#002984"));
                         submissions.setTextColor(Color.parseColor("#002984"));
                        submissionsextra.setTextColor(Color.parseColor("#002984"));

                        heading.setTextColor(Color.parseColor("#002984"));
                         barcharttext.setTextColor(Color.parseColor("#002984"));
                    }else if(plist.get(0).getRank().toLowerCase().equals("specialist")){
                        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#81c784")));
                        window.setStatusBarColor(Color.parseColor("#519657"));
                        tagcard.setCardBackgroundColor(Color.parseColor("#81c784"));
                        tagstext.setTextColor(Color.parseColor("#519657"));
                        submissions.setTextColor(Color.parseColor("#519657"));
                        submissionsextra.setTextColor(Color.parseColor("#519657"));
                        heading.setTextColor(Color.parseColor("#519657"));
                        barcharttext.setTextColor(Color.parseColor("#519657"));

                    }else if(plist.get(0).getRank().toLowerCase().equals("pupil")){
                        tagcard.setCardBackgroundColor(Color.parseColor("#43a047"));
                        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#43a047")));
                        window.setStatusBarColor(Color.parseColor("#00701a"));
                        tagstext.setTextColor(Color.parseColor("#00701a"));
                        submissions.setTextColor(Color.parseColor("#00701a"));
                        submissionsextra.setTextColor(Color.parseColor("#00701a"));

                        heading.setTextColor(Color.parseColor("#00701a"));
                        barcharttext.setTextColor(Color.parseColor("#00701a"));
                    }else if(plist.get(0).getRank().toLowerCase().equals("newbie")){
                        tagcard.setCardBackgroundColor(Color.parseColor("#78909c"));
                        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#78909c")));
                        window.setStatusBarColor(Color.parseColor("#4b636e"));
                        tagstext.setTextColor(Color.parseColor("#4b636e"));
                        submissions.setTextColor(Color.parseColor("#4b636e"));
                        submissionsextra.setTextColor(Color.parseColor("#4b636e"));

                        heading.setTextColor(Color.parseColor("#4b636e"));
                        barcharttext.setTextColor(Color.parseColor("#4b636e"));

                    }
                    else if(plist.get(0).getRank().toLowerCase().equals("master")){
                        tagcard.setCardBackgroundColor(Color.parseColor("#ffa726"));
                        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffa726")));
                        window.setStatusBarColor(Color.parseColor("#c77800"));
                        tagstext.setTextColor(Color.parseColor("#c77800"));
                        submissions.setTextColor(Color.parseColor("#c77800"));
                        submissionsextra.setTextColor(Color.parseColor("#c77800"));
                        heading.setTextColor(Color.parseColor("#c77800"));
                        barcharttext.setTextColor(Color.parseColor("#c77800"));

                    }  else if(plist.get(0).getRank().toLowerCase().equals("international master")){
                        tagcard.setCardBackgroundColor(Color.parseColor("#ffa726"));
                        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffa726")));
                        window.setStatusBarColor(Color.parseColor("#c77800"));
                        tagstext.setTextColor(Color.parseColor("#c77800"));
                        submissions.setTextColor(Color.parseColor("#c77800"));
                        submissionsextra.setTextColor(Color.parseColor("#c77800"));
                        heading.setTextColor(Color.parseColor("#c77800"));
                        barcharttext.setTextColor(Color.parseColor("#002984"));
                    }
                    else if(plist.get(0).getRank().toLowerCase().equals("candidate master")){
                        tagcard.setCardBackgroundColor(Color.parseColor("#7b1fa2"));
                        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7b1fa2")));
                        window.setStatusBarColor(Color.parseColor("#4a0072"));
                        tagstext.setTextColor(Color.parseColor("#4a0072"));
                        submissions.setTextColor(Color.parseColor("#4a0072"));
                        submissionsextra.setTextColor(Color.parseColor("#4a0072"));
                        heading.setTextColor(Color.parseColor("#4a0072"));
                        barcharttext.setTextColor(Color.parseColor("#4a0072"));
                    }
                    else if(plist.get(0).getRank().toLowerCase().equals("grandmaster")){
                        tagcard.setCardBackgroundColor(Color.parseColor("#e53935"));
                        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e53935")));
                        window.setStatusBarColor(Color.parseColor("#ab000d"));
                        tagstext.setTextColor(Color.parseColor("#ab000d"));
                        submissions.setTextColor(Color.parseColor("#ab000d"));
                        submissionsextra.setTextColor(Color.parseColor("#ab000d"));
                        heading.setTextColor(Color.parseColor("#ab000d"));
                        barcharttext.setTextColor(Color.parseColor("#ab000d"));
                    }else if(plist.get(0).getRank().toLowerCase().equals("international grandmaster")){
                        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e53935")));
                        tagcard.setCardBackgroundColor(Color.parseColor("#3f51b5"));
                        window.setStatusBarColor(Color.parseColor("#e53935"));
                        tagstext.setTextColor(Color.parseColor("#e53935"));
                        submissions.setTextColor(Color.parseColor("#e53935"));
                        submissionsextra.setTextColor(Color.parseColor("#e53935"));
                        heading.setTextColor(Color.parseColor("#e53935"));
                        barcharttext.setTextColor(Color.parseColor("#e53935"));
                    }else if(plist.get(0).getRank().toLowerCase().equals("legendary grandmaster")){
                        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e53935")));
                        window.setStatusBarColor(Color.parseColor("#ab000d"));
                        tagcard.setCardBackgroundColor(Color.parseColor("#e53935"));
                        tagstext.setTextColor(Color.parseColor("#ab000d"));
                        submissions.setTextColor(Color.parseColor("#ab000d"));
                        submissionsextra.setTextColor(Color.parseColor("#ab000d"));
                        heading.setTextColor(Color.parseColor("#ab000d"));
                        barcharttext.setTextColor(Color.parseColor("#ab000d"));
                    }}
                    myAdapter= new MyAdapter(MainActivity.this,plist,plist.get(0).getRank());
                    rv.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                 }
                 else{
                     Toast.makeText(MainActivity.this,"Enter VALID user handle",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,HomePage.class);
                startActivity(intent);
                finish();
            }}

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                {
                    Toast.makeText(MainActivity.this,"Enter VALID user handle",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,HomePage.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        Call<usersubmissioninfo> call1=myWebService.getUserSubmissions(user,1,5000);

        call1.enqueue(new Callback<usersubmissioninfo>() {
            @Override
            public void onResponse(Call<usersubmissioninfo> call, Response<usersubmissioninfo> response) {
               if(response.isSuccessful()){
                 //   Toast.makeText(MainActivity.this,"Submissions are also accessed",Toast.LENGTH_LONG).show();
                    authorinfolist=new ArrayList<>();
                    probleminfolist=new ArrayList<>();

                    HashMap<String,Integer> verdict=new HashMap<>();
                   HashMap<String,Integer> verdictoncontest=new HashMap<>();
                   HashMap<String,Integer> verdictonpractice=new HashMap<>();
                   HashMap<String,Integer> verdictonvirtual=new HashMap<>();
                   HashMap<Integer,Integer> questionswithratingx=new HashMap<>();
                   HashMap<Integer,Integer> questionswithratingxonpractice=new HashMap<>();
                   HashMap<Integer,Integer> questionswithratingxoncontest=new HashMap<>();
                   HashMap<Integer,Integer> questionswithratingxonvirtual=new HashMap<>();
                   HashMap<String,HashMap<Integer,Integer>> tagswithratingxoncontest=new HashMap<>();
                   HashMap<String,HashMap<Integer,Integer>> tagswithratingx=new HashMap<>();
                   HashMap<String,HashMap<Integer,Integer>> tagswithratingxonpractice=new HashMap<>();
                   HashMap<String,HashMap<Integer,Integer>> tagswithratingxonvirtual=new HashMap<>();
                    List<Result1> r1=response.body().getResult();
                    for(Result1 r:r1){
                        String ver=r.getVerdict();
                        verdict.put(ver,verdict.getOrDefault(ver,0)+1);
                        if(r.getAuthor().getParticipantType().equals("CONTESTANT")||r.getAuthor().getParticipantType().equals("OUT_OF_COMPETITION")){
                            verdictoncontest.put(ver,verdictoncontest.getOrDefault((ver),0)+1);
                        }
                        else if(r.getAuthor().getParticipantType().equals("PRACTICE")){
                            verdictonpractice.put(ver,verdictonpractice.getOrDefault(ver,0)+1);
                        }
                        else if(r.getAuthor().getParticipantType().equals("VIRTUAL")){
                            verdictonvirtual.put(ver,verdictonvirtual.getOrDefault(ver,0)+1);
                        }
                        if(ver==null) continue;
                        if(ver.equals("OK")){
                            Problem p=r.getProblem();
                            int rating =p.getRating();
                            questionswithratingx.put(rating,questionswithratingx.getOrDefault(rating,0)+1);
                            List<String> questionlist1=r.getProblem().getTags();
                            for(String s:questionlist1) {
                                if(!tagswithratingx.containsKey(s)){
                                    HashMap<Integer,Integer> h=new HashMap<>();
                                    h.put(rating,1);
                                    tagswithratingx.put(s,h);
                                }else {
                                    HashMap<Integer,Integer> h=tagswithratingx.get(s);
                                    h.put(rating,h.getOrDefault(rating,0)+1);
                                    tagswithratingx.put(s,h);
                                }
                            }
                            if(r.getAuthor().getParticipantType().equals("CONTESTANT")||r.getAuthor().getParticipantType().equals("OUT_OF_COMPETITION")){
                                questionswithratingxoncontest.put(rating,questionswithratingxoncontest.getOrDefault(rating,0)+1);
                                List<String> questionlist=r.getProblem().getTags();
                                for(String s:questionlist) {
                                    if(!tagswithratingxoncontest.containsKey(s)){
                                        HashMap<Integer,Integer> h=new HashMap<>();
                                        h.put(rating,1);
                                        tagswithratingxoncontest.put(s,h);
                                    }else {
                                        HashMap<Integer,Integer> h=tagswithratingxoncontest.get(s);
                                        h.put(rating,h.getOrDefault(rating,0)+1);
                                        tagswithratingxoncontest.put(s,h);
                                    }
                                }
                            }
                            else if(r.getAuthor().getParticipantType().equals("VIRTUAL")){
                                 questionswithratingxonvirtual.put(rating,questionswithratingxonvirtual.getOrDefault(rating,0)+1);
                                List<String> questionlist=r.getProblem().getTags();
                                for(String s:questionlist) {
                                    if(!tagswithratingxonvirtual.containsKey(s)){
                                        HashMap<Integer,Integer> h=new HashMap<>();
                                        h.put(rating,1);
                                        tagswithratingxonvirtual.put(s,h);
                                    }else {
                                        HashMap<Integer,Integer> h=tagswithratingxonvirtual.get(s);
                                        h.put(rating,h.getOrDefault(rating,0)+1);
                                        tagswithratingxonvirtual.put(s,h);
                                    }
                                }
                            }
                            else if(r.getAuthor().getParticipantType().equals("PRACTICE")){
                                questionswithratingxonpractice.put(rating,questionswithratingxonpractice.getOrDefault(rating,0)+1);
                                List<String> questionlist=r.getProblem().getTags();
                                for(String s:questionlist) {
                                    if(!tagswithratingxonpractice.containsKey(s)){
                                        HashMap<Integer,Integer> h=new HashMap<>();
                                        h.put(rating,1);
                                        tagswithratingxonpractice.put(s,h);
                                    }else {
                                        HashMap<Integer,Integer> h=tagswithratingxonpractice.get(s);
                                        h.put(rating,h.getOrDefault(rating,0)+1);
                                        tagswithratingxonpractice.put(s,h);
                                    }
                                }
                            }
                        }
                    }

                   if(category.equals("contest")){

                       //  piechart.setHoleColor(true);
                        heading.setText("CONTEST DATA");
                       BarChart hb1;
                       hb1=(BarChart)findViewById(R.id.hb1);
                       List<BarEntry> yVals=new ArrayList<>();
                       float barWidth=50f;
                       float spacebwBar=10f;
                       for(Integer i:questionswithratingxoncontest.keySet()){
                           yVals.add(new BarEntry(i,questionswithratingxoncontest.get(i)));
                       }
                       BarDataSet set1=new BarDataSet(yVals,"X axis-->Problem Rating   Y axis-->Count of Correct Submissions");
                       hb1.getXAxis().setSpaceMax(0f);
                       set1.setColor(Color.RED);
                       hb1.setDrawValueAboveBar(true);
                       set1.setDrawValues(true);
                       hb1.getXAxis().setDrawGridLines(true);
                       hb1.getXAxis().setSpaceMax(1);
                       //    hb1.getAxisLeft().setAxisMaximum(10); hb1.getAxisLeft().setAxisMinimum(0);
                       BarData data1=new BarData(set1);

                       data1.setBarWidth(barWidth);
                       hb1.setData(data1);
                       hb1.invalidate();;
                       List<Question> tagsandcount=new ArrayList<>();
                       Tagadapter tagadapter=new Tagadapter(MainActivity.this,tagsandcount,plist.get(0).getRank());
                       tagsrv.setAdapter(tagadapter);
                       for(String s:tagswithratingxoncontest.keySet()){
                           HashMap<Integer,Integer> h=tagswithratingxoncontest.get(s);
                             int count=0;
                             for(Integer i:h.keySet()){
                                 count+=h.get(i);
                             }
                             tagsandcount.add(new Question(s,count));
                         }
                             tagadapter.notifyDataSetChanged();

                      PieChart piechart=(PieChart) findViewById(R.id.piechart);
                       piechart.setDrawEntryLabels(false);
                       piechart.setDrawHoleEnabled(true);
                       piechart.setHoleRadius(40);
                       piechart.setUsePercentValues(false);
                       piechart.setCenterText("SUBMISSIONS");
                       piechart.setCenterTextSize(20);

                       piechart.getDefaultValueFormatter();
                       piechart.setCenterTextRadiusPercent(80);//-->the percentage of the area of the center the text wants to cover
                       piechart.getDescription().setEnabled(true);
                       piechart.setExtraOffsets(5,10,5,5);
                       piechart.setDragDecelerationFrictionCoef(.5f);
                       //   piechart.setDrawHoleEnabled(false);
                       piechart.setCenterTextColor(Color.BLACK);
                       ArrayList<PieEntry> yValues=new ArrayList<>();
                       for(String ver:verdictoncontest.keySet()){
                           yValues.add(new PieEntry((verdictoncontest.get(ver)),ver));
                       }
                       PieDataSet dataset=new PieDataSet(yValues,"Status");
                       dataset.setSliceSpace(0f);
                       dataset.setSelectionShift(3f);
                       dataset.setColors(ColorTemplate.JOYFUL_COLORS);
                       //apna array of colors bana k bhi uppar paas kar sakte hai but in that case I should
                       // know how many entry are there in piechart

                       PieData data=new PieData(dataset);
                       data.setValueTextSize(10f);
                       data.setHighlightEnabled(true);
                       //     data.setValueFormatter(new PercentFormatter());
                       data.setValueTextColor(Color.BLACK);
                       piechart.setData(data);
                       piechart.invalidate();

                   }
                   else if(category.equals("practice")){
                       heading.setText("PRACTICE DATA");
                       piechart=(PieChart) findViewById(R.id.piechart);
                       piechart.setUsePercentValues(false);
                       piechart.getDescription().setEnabled(true);
                       piechart.setExtraOffsets(5,10,5,5);
                       piechart.setDragDecelerationFrictionCoef(.5f);
                       piechart.setDrawHoleEnabled(false);
                       piechart.setCenterTextColor(Color.BLACK);
                       //  piechart.setHoleColor(true);
                       ArrayList<PieEntry> yValues=new ArrayList<>();
                       for(String ver:verdictonpractice.keySet()){
                           yValues.add(new PieEntry((verdictonpractice.get(ver)),ver));
                       }
                       PieDataSet dataset=new PieDataSet(yValues,"Status");
                       dataset.setSliceSpace(0f);
                       dataset.setSelectionShift(0f);
                       dataset.setColors(ColorTemplate.JOYFUL_COLORS);
                       PieData data=new PieData(dataset);
                       data.setValueTextSize(10f);
                       data.setHighlightEnabled(false);
                      // data.setValueFormatter(new PercentFormatter());
                       data.setValueTextColor(Color.BLACK);
                       piechart.setData(data);piechart.invalidate();
                       BarChart hb1;
                       piechart.setDrawEntryLabels(false);
                       piechart.setDrawHoleEnabled(true);
                       piechart.setHoleRadius(40);
                       piechart.setUsePercentValues(false);

                       hb1=(BarChart)findViewById(R.id.hb1);
                       hb1.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {

                           @Override
                           public String getFormattedValue(float value, AxisBase axis) {
                               return String.valueOf((int)value);
                           }
                       });
                       List<BarEntry> yVals=new ArrayList<>();
                       float barWidth=50f;
                       float spacebwBar=10f;
                       for(Integer i:questionswithratingxonpractice.keySet()){
                           yVals.add(new BarEntry(i,questionswithratingxonpractice.get(i)));
                       }
                       BarDataSet set1=new BarDataSet(yVals,"X axis-->Problem Rating   Y axis-->Count of Correct Submissions");
                       hb1.getXAxis().setSpaceMax(0f);
                       set1.setColor(Color.RED);
                       hb1.setDrawValueAboveBar(true);
                       set1.setDrawValues(true);
                       hb1.getXAxis().setDrawGridLines(true);
                       hb1.getXAxis().setSpaceMax(1);
                       //    hb1.getAxisLeft().setAxisMaximum(10); hb1.getAxisLeft().setAxisMinimum(0);
                       BarData data1=new BarData(set1);

                       data1.setBarWidth(barWidth);
                       hb1.setData(data1);
                       hb1.invalidate();;
                       List<Question> tagsandcount=new ArrayList<>();
                       Tagadapter tagadapter=new Tagadapter(MainActivity.this,tagsandcount,plist.get(0).getRank());
                       tagsrv.setAdapter(tagadapter);
                       for(String s:tagswithratingxonpractice.keySet()){
                           HashMap<Integer,Integer> h=tagswithratingxonpractice.get(s);
                           int count=0;
                           for(Integer i:h.keySet()){
                               count+=h.get(i);
                           }
                           tagsandcount.add(new Question(s,count));
                       }
                       tagadapter.notifyDataSetChanged();
                   }
                   else if(category.equals("virtual")){
                       heading.setText("VIRTUAL PARTICIPATION DATA");

                       piechart=(PieChart) findViewById(R.id.piechart);
                       piechart.setUsePercentValues(false);
                       piechart.getDescription().setEnabled(true);
                       piechart.setExtraOffsets(5,10,5,5);
                       piechart.setDragDecelerationFrictionCoef(.5f);
                       piechart.setDrawHoleEnabled(false);
                       piechart.setCenterTextColor(Color.BLACK);
                       //  piechart.setHoleColor(true);
                       ArrayList<PieEntry> yValues=new ArrayList<>();
                       for(String ver:verdictonvirtual.keySet()){
                           yValues.add(new PieEntry((verdictonvirtual.get(ver)),ver));
                       }
                       PieDataSet dataset=new PieDataSet(yValues,"Status");
                       dataset.setSliceSpace(0f);
                       dataset.setSelectionShift(5f);
                       dataset.setColors(ColorTemplate.JOYFUL_COLORS);
                       PieData data=new PieData(dataset);
                       data.setValueTextSize(10f);
                       data.setHighlightEnabled(false);
                      // data.setValueFormatter(new PercentFormatter());
                       data.setValueTextColor(Color.BLACK);
                       piechart.setData(data);
                       piechart.invalidate();
                       BarChart hb1;
                       hb1=(BarChart)findViewById(R.id.hb1);
                       List<BarEntry> yVals=new ArrayList<>();
                       float barWidth=50f;
                       float spacebwBar=10f;
                       for(Integer i:questionswithratingxonvirtual.keySet()){
                           yVals.add(new BarEntry(i,questionswithratingxonvirtual.get(i)));
                       }
                       piechart.setDrawEntryLabels(false);
                       piechart.setDrawHoleEnabled(true);
                       piechart.setHoleRadius(40);
                       piechart.setUsePercentValues(false);

                       BarDataSet set1=new BarDataSet(yVals,"X axis-->Problem Rating   Y axis-->Count of Correct Submissions");
                       hb1.getXAxis().setSpaceMax(0f);
                       set1.setColor(Color.RED);
                       hb1.setDrawValueAboveBar(true);
                       set1.setDrawValues(true);
                       hb1.getXAxis().setDrawGridLines(true);
                       hb1.getXAxis().setSpaceMax(1);
                       //    hb1.getAxisLeft().setAxisMaximum(10); hb1.getAxisLeft().setAxisMinimum(0);
                       BarData data1=new BarData(set1);

                       data1.setBarWidth(barWidth);
                       hb1.setData(data1);
                       hb1.invalidate();;
                       List<Question> tagsandcount=new ArrayList<>();
                       Tagadapter tagadapter=new Tagadapter(MainActivity.this,tagsandcount,plist.get(0).getRank());
                       tagsrv.setAdapter(tagadapter);
                       for(String s:tagswithratingxonvirtual.keySet()){
                           HashMap<Integer,Integer> h=tagswithratingxonvirtual.get(s);
                           int count=0;
                           for(Integer i:h.keySet()){
                               count+=h.get(i);
                           }
                           tagsandcount.add(new Question(s,count));
                       }

                       tagadapter.notifyDataSetChanged();
                   }

                }
            }

            @Override
            public void onFailure(Call<usersubmissioninfo> call, Throwable t) {

            }
        });


    }
}
