package com.danielme.android.bottomsheetmenu;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomSheetMenuFragment extends BottomSheetDialogFragment {

  private static final String ARG_LAYOUT_ID = "ARG_LAYOUT_ID";
  private static final int LIST = R.layout.fragment_list_bottom_sheet;
  private static final int GRID = R.layout.fragment_grid_bottom_sheet;

  public static BottomSheetMenuFragment createInstanceList() {
    return getInstance(LIST);
  }

  public static BottomSheetMenuFragment createInstanceGrid() {
    return getInstance(GRID);
  }

  private static BottomSheetMenuFragment getInstance(int layoutId) {
    Bundle args = new Bundle();
    args.putInt(ARG_LAYOUT_ID, layoutId);
    BottomSheetMenuFragment frag = new BottomSheetMenuFragment();
    frag.setArguments(args);
    return frag;
  }

  private final BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new
      BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
          String state = null;

          switch (newState) {
            case BottomSheetBehavior.STATE_COLLAPSED:
              state = "STATE_COLLAPSED";
              break;
            case BottomSheetBehavior.STATE_DRAGGING:
              state = "STATE_DRAGGING";
              break;
            case BottomSheetBehavior.STATE_EXPANDED:
              state = "STATE_EXPANDED";
              break;
            case BottomSheetBehavior.STATE_SETTLING:
              state = "STATE_SETTLING";
              break;
            case BottomSheetBehavior.STATE_HIDDEN:
              state = "STATE_HIDDEN";
              //call ALWAYS dismiss to hide the modal background
              dismiss();
              break;
          }

          Log.d(BottomSheetMenuFragment.class.getSimpleName(), state);
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
          Log.d(BottomSheetMenuFragment.class.getSimpleName(), String.valueOf(slideOffset));
        }
      };

  @Override
  public void setupDialog(Dialog dialog, int style) {
    //noinspection RestrictedApi
    super.setupDialog(dialog, style);

    View contentView = View.inflate(getContext(), getArguments().getInt(ARG_LAYOUT_ID), null);
    ButterKnife.bind(this, contentView);
    dialog.setContentView(contentView);

    BottomSheetBehavior<View> mBottomSheetBehavior = BottomSheetBehavior.from(((View) contentView
        .getParent()));
    if (mBottomSheetBehavior != null) {
      mBottomSheetBehavior.setBottomSheetCallback(mBottomSheetBehaviorCallback);
      mBottomSheetBehavior.setPeekHeight(1200);
    }
  }

  @OnClick({R.id.youtube, R.id.lastfm, R.id.google, R.id.musicbrainz, R.id.amazon, R.id.play})
  public void onClickBottomSheet(View view) {
    Toast.makeText(getContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
    dismiss();
  }

}