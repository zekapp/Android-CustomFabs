package com.android_customfabs;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.android_customfabs.dummy.DummyContent;
import com.customfabs.FloatingActionBackPanel;
import com.customfabs.FloatingActionButton;
import com.customfabs.FloatingActionMenu;
import com.customfabs.SubActionButton;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

//        setUpFab();
        setUpFabAttached();

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setUpFabAttached() {
        Button fabIconNew = (Button) findViewById(R.id.centerActionButton);

        fabIconNew.setBackground(getResources().getDrawable(R.drawable.button_action_selector));

        // SubFab button
        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_place));
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.sub_action_button_selector));

        ImageView itemIco2 = new ImageView(this);
        itemIco2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_settings));
        SubActionButton.Builder itemBuilder2 = new SubActionButton.Builder(this);
        itemBuilder2.setBackgroundDrawable(getResources().getDrawable(R.drawable.sub_action_button_selector));

        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_video));
        SubActionButton.Builder itemBuilder3 = new SubActionButton.Builder(this);
        itemBuilder3.setBackgroundDrawable(getResources().getDrawable(R.drawable.sub_action_button_selector));

        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();
        SubActionButton button2 = itemBuilder2.setContentView(itemIco2).build();
        SubActionButton button3 = itemBuilder3.setContentView(itemIcon3).build();

        // SubFab button
        ImageView backPanelImage = new ImageView(this);
        FloatingActionBackPanel.Builder backPanelBuilder = new FloatingActionBackPanel.Builder(this);
        backPanelBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle));
        FloatingActionBackPanel backPanel = backPanelBuilder.setContentView(backPanelImage).build();


        // Menu Manager
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addBackPanelView(backPanel)
                .attachTo(fabIconNew)
                .build();
    }
    private void setUpFab() {

        // FAB Button
        final ImageView fabIconNew = new ImageView(this);
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new_light));
        fabIconNew.setBackground(getResources().getDrawable(R.drawable.button_action_selector));
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(fabIconNew)
                .build();

        // SubFab button
        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_place));
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.sub_action_button_selector));

        ImageView itemIco2 = new ImageView(this);
        itemIco2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_settings));
        SubActionButton.Builder itemBuilder2 = new SubActionButton.Builder(this);
        itemBuilder2.setBackgroundDrawable(getResources().getDrawable(R.drawable.sub_action_button_selector));

        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_video));
        SubActionButton.Builder itemBuilder3 = new SubActionButton.Builder(this);
        itemBuilder3.setBackgroundDrawable(getResources().getDrawable(R.drawable.sub_action_button_selector));

        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();
        SubActionButton button2 = itemBuilder2.setContentView(itemIco2).build();
        SubActionButton button3 = itemBuilder3.setContentView(itemIcon3).build();


        // SubFab button
        ImageView backPanelImage = new ImageView(this);
        FloatingActionBackPanel.Builder backPanelBuilder = new FloatingActionBackPanel.Builder(this);
        backPanelBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle));
        FloatingActionBackPanel backPanel = backPanelBuilder.setContentView(backPanelImage).build();


        // Menu Manager
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addBackPanelView(backPanel)
                .attachTo(rightLowerButton)
                .build();

        actionMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                fabIconNew.setRotation(0);
                PropertyValuesHolder pvh = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvh);
                animator.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                fabIconNew.setRotation(45);
                PropertyValuesHolder pvh = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvh);
                animator.start();
            }
        });

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
