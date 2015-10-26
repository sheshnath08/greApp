package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sheshnath.grevocabularybuilder.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sheshnath on 10/9/2015.
 */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private final List<WordModel> mModels;

    public WordListAdapter(Context context, List<WordModel> models) {
        mInflater = LayoutInflater.from(context);
        mModels = new ArrayList<>(models);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = mInflater.inflate(R.layout.singleword, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WordModel model = mModels.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public void animateTo(List<WordModel> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }


    private void applyAndAnimateRemovals(List<WordModel> newModels) {
        for (int i = mModels.size() - 1; i >= 0; i--) {
            final WordModel model = mModels.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<WordModel> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final WordModel model = newModels.get(i);
            if (!mModels.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<WordModel> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final WordModel model = newModels.get(toPosition);
            final int fromPosition = mModels.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public WordModel removeItem(int position) {
        final WordModel model = mModels.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, WordModel model) {
        mModels.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final WordModel model = mModels.remove(fromPosition);
        mModels.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    // Below class is for ViewHolder that inflates view to the list.

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private final TextView wordText;
        private final TextView meaningText;

        public ViewHolder(View itemView) {
            super(itemView);

            wordText = (TextView) itemView.findViewById(R.id.wordTextView);
            meaningText = (TextView) itemView.findViewById(R.id.meaningTextView);
        }

        public void bind(WordModel model)
        {
            wordText.setText(model.getWord());
            meaningText.setText(model.getMeaning());
        }
    }

}
