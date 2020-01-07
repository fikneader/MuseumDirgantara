package tniau.id.museumdirgantara;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import tniau.id.museumdirgantara.Function.Tools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InfoFragment extends Fragment {

    private static final int MAX_STEP = 6;

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private Button btnNext;
    private String about_title_array[] = {
            "Menu Berita",
            "Menu Sejarah",
            "Menu Pahlawan",
            "Menu Ruangan",
            "Menu Reservasi",
            "Menu Kritik dan Saran"
    };
    private String about_description_array[] = {
            "Lihat berita terbaru tentang Museum Dirgantara dan ketahui informasi yang menarik dari halaman facebook Museum Dirgantara Mandala",
            "Ketahui bagaimana Museum Dirgantara Mandala dapat berdiri hingga saat ini dan sejarah penting mengenai Museum Dirgantara Mandala",
            "Berisi informasi menarik daftar nama - nama pahlawan dan tokoh - tokoh pengharum TNI AU",
            "Jelajahi setiap ruangan yang ada di Museum Dirgantara Mandala dan dapatkan wawasan dari koleksi - koleksi menarik Museum Dirgantara Mandala",
            "Nggak perlu repot lagi dan lebih mudah mengunjungi Museum Dirgantara Mandala dengan Menu Reservasi",
            "Demi melayani dengan kenyamanan kami sangat mengapresiasi kritikan dan masukan untuk Museum Dirgantara Mandala"
    };
    private int about_images_array[] = {
            R.drawable.berita,
            R.drawable.sejarah,
            R.drawable.pahlawan,
            R.drawable.ruangan,
            R.drawable.reservasi,
            R.drawable.kritiksaran
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        btnNext = (Button) view.findViewById(R.id.btn_next);
        dotsLayout = (LinearLayout) view.findViewById(R.id.layoutDots);

        // adding bottom dots
        bottomProgressDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem() + 1;
                if (current < MAX_STEP) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    getActivity().finish();
                }
            }
        });

        ((ImageButton)view.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

//        Tools.setSystemBarColor(getActivity(), R.color.colorPrimary);
//        Tools.setSystemBarLight(getActivity());


        return  view;
    }

    private void bottomProgressDots(int current_index) {
        ImageView[] dots = new ImageView[MAX_STEP];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getActivity());
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        }
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            bottomProgressDots(position);

            if (position == about_title_array.length - 1) {
                btnNext.setText("LANJUT");
                btnNext.setBackgroundColor(getResources().getColor(R.color.orange_400));
                btnNext.setTextColor(Color.WHITE);

            } else {
                btnNext.setText("SELESAI");
                btnNext.setBackgroundColor(getResources().getColor(R.color.grey_10));
                btnNext.setTextColor(getResources().getColor(R.color.grey_90));
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.item_stepper_info, container, false);
            ((TextView) view.findViewById(R.id.title)).setText(about_title_array[position]);
            ((TextView) view.findViewById(R.id.description)).setText(about_description_array[position]);
            ((ImageView) view.findViewById(R.id.image)).setImageResource(about_images_array[position]);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return about_title_array.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
