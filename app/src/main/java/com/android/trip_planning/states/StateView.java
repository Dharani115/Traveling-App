package com.android.trip_planning.states;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.trip_planning.Activity.NavigatonDrawer;
import com.android.trip_planning.R;

import java.util.ArrayList;
import java.util.Arrays;

import static maes.tech.intentanim.CustomIntent.customType;

public class StateView extends ListActivity
{
    private String[] listview_names =  {"Andhra Pradesh",
            "Arunachal Pradesh ",
            "Assam",
            "Bihar",
            "Chhattisgarh",
            "Goa ",
            "Gujarat",
            "Haryana",
            "Himachal Pradesh",
            "Jharkhand",
            "Karnataka",
            "Kerala",
            "Madhya Pradesh",
            "Maharashtra",
            "Manipur",
            "Meghalaya",
            "Mizoram",
            "Nagaland",
            "Odisha",
            "Punjab ",
            "Rajasthan",
            "Sikkim",
            "Tamil Nadu",
            "Telangana",
            "Tripura",
            "Uttar Pradesh",
            "Uttarakhand",
            "West Bengal"
    };
    static Context mcontext;
    private static int[] listview_images =
            {
                    R.drawable.andhrapradhesh,
                    R.drawable.arunachalpradesh,
                    R.drawable.assam,
                    R.drawable.bihar,
                    R.drawable.chhattisgarh,
                    R.drawable.goa,
                    R.drawable.gujarat,
                    R.drawable.haryana,
                    R.drawable.himachalpradesh,
                    R.drawable.jharkhand,
                    R.drawable.karnataka,
                    R.drawable.kerala,
                    R.drawable.madhyapradhesh,
                    R.drawable.maharastra,
                    R.drawable.manipur,
                    R.drawable.meghalaya,
                    R.drawable.mizoram,
                    R.drawable.nagaland,
                    R.drawable.odisha,
                    R.drawable.punjab,
                    R.drawable.rajasthan,
                    R.drawable.sikkim,
                    R.drawable.tamilnadu,
                    R.drawable.telangana,
                    R.drawable.tripura,
                    R.drawable.uttarpradhesh,
                    R.drawable.uttarkhand,
                    R.drawable.westbengal
            };
    private ListView lv;
    private TextView Tvg;
    private static ArrayList<String> array_sort;
    private static ArrayList<Integer> image_sort;
    private Intent AndhraPradhesh;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_view);
        back =(ImageView) findViewById(R.id.stateback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StateView.this, NavigatonDrawer.class);
                startActivity(intent);
                finish();
                customType(StateView.this,"right-to-left");

            }
        });
        final ListView lv= (ListView) findViewById(android.R.id.list);
        array_sort=new ArrayList<String> (Arrays.asList(listview_names));
        image_sort=new ArrayList<Integer>();
        for (int index = 0; index < listview_images.length; index++)
        {
            image_sort.add(listview_images[index]);
        }
        setListAdapter(new bsAdapter(this));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent(StateView.this,AndhraPradhesh.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 1){
                    Intent intent = new Intent(StateView.this,ArunachalPradesh.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 2){
                    Intent intent = new Intent(StateView.this,Assam.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 3){
                    Intent intent = new Intent(StateView.this,Bihar.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 4){
                    Intent intent = new Intent(StateView.this,Chhattisgarh.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 5){
                    Intent intent = new Intent(StateView.this, Goa.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 6){
                    Intent intent = new Intent(StateView.this, Gujarath.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 7){
                    Intent intent = new Intent(StateView.this, Hariyana.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 8){
                    Intent intent = new Intent(StateView.this, HimachalPradesh.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 9){
                    Intent intent = new Intent(StateView.this, Jharkhand.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 10){
                    Intent intent = new Intent(StateView.this, Karnataka.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 11){
                    Intent intent = new Intent(StateView.this, Kerala.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 12){
                    Intent intent = new Intent(StateView.this, MadhiyaPradesh.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 13){
                    Intent intent = new Intent(StateView.this, Maharastra.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 14){
                    Intent intent = new Intent(StateView.this, Manipur.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 15){
                    Intent intent = new Intent(StateView.this, Meghalaya.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 16){
                    Intent intent = new Intent(StateView.this, Mizoram.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 17){
                    Intent intent = new Intent(StateView.this, Nagaland.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 18){
                    Intent intent = new Intent(StateView.this, Odisha.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 19){
                    Intent intent = new Intent(StateView.this, Punjab.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 20){
                    Intent intent = new Intent(StateView.this, Rajasthan.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 21){
                    Intent intent = new Intent(StateView.this, Sikkim.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 22){
                    Intent intent = new Intent(StateView.this, TamilNadu.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 23){
                    Intent intent = new Intent(StateView.this, Telangana.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 24){
                    Intent intent = new Intent(StateView.this, Tripura.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 25){
                    Intent intent = new Intent(StateView.this, UttarPradesh.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 26){
                    Intent intent = new Intent(StateView.this, Uttarakhand.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
                else if (position == 27){
                    Intent intent = new Intent(StateView.this, WestBengal.class);
                    startActivity(intent);
                    customType(StateView.this,"left-to-right");

                }
            }
        });
    }
    public static class bsAdapter extends BaseAdapter
    {
        Activity cntx;
        public bsAdapter(Activity context)
        {
            // TODO Auto-generated constructor stub
            this.cntx=context;
        }
        public int getCount()
        {
            // TODO Auto-generated method stub
            return array_sort.size();
        }
        public Object getItem(int position)
        {
            // TODO Auto-generated method stub
            return array_sort.get(position);
        }
        public long getItemId(int position)
        {
            // TODO Auto-generated method stub
            return array_sort.size();
        }
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View row=null;
            LayoutInflater inflater=cntx.getLayoutInflater();
            row=inflater.inflate(R.layout.list_view, null);
            TextView tv = (TextView) row.findViewById(R.id.title);
            ImageView im = (ImageView) row.findViewById(R.id.imagevie);
            tv.setText(array_sort.get(position));

            im.setImageBitmap(getRoundedShape(decodeFile(cntx, listview_images[position]),200));

            return row;
        }

        public static Bitmap decodeFile(Context context,int resId) {
            try {
                mcontext=context;
                BitmapFactory.Options o = new BitmapFactory.Options();
                o.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(mcontext.getResources(), resId, o);
                final int REQUIRED_SIZE = 200;
                int width_tmp = o.outWidth, height_tmp = o.outHeight;
                int scale = 1;
                while (true)
                {
                    if (width_tmp / 2 < REQUIRED_SIZE
                            || height_tmp / 2 < REQUIRED_SIZE)
                        break;
                    width_tmp /= 2;
                    height_tmp /= 2;
                    scale++;
                }
                BitmapFactory.Options o2 = new BitmapFactory.Options();
                o2.inSampleSize = scale;
                return BitmapFactory.decodeResource(mcontext.getResources(), resId, o2);
            } catch (Exception e) {
            }
            return null;
        }
    }
    public static Bitmap getRoundedShape(Bitmap scaleBitmapImage,int width) {
        // TODO Auto-generated method stub
        int targetWidth = width;
        int targetHeight = width;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);
        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth,
                        targetHeight), null);
        return targetBitmap;
    }
}
