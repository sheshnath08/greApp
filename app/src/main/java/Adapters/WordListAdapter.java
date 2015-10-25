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
