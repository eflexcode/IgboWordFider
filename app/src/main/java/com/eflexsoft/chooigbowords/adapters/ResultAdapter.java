package com.eflexsoft.chooigbowords.adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eflexsoft.chooigbowords.databinding.ResponseItemBinding;
import com.eflexsoft.chooigbowords.model.IgboApiResponse;

import java.io.IOException;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    List<IgboApiResponse> igboApiResponseList;
    Context context;

    Handler handler = new Handler();

    public ResultAdapter(List<IgboApiResponse> igboApiResponseList, Context context) {
        this.igboApiResponseList = igboApiResponseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        ResponseItemBinding binding = ResponseItemBinding.inflate(layoutInflater, parent, false);

        return new ResultViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        IgboApiResponse igboApiResponse = igboApiResponseList.get(position);

        holder.binding.word.setText(igboApiResponse.getWord());


        if (igboApiResponse.getAttributes().isStandardIgbo()) {
            holder.binding.standardIgbo.setVisibility(View.VISIBLE);
        } else {
            holder.binding.standardIgbo.setVisibility(View.GONE);
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < igboApiResponse.getDefinitions().size(); i++) {

            stringBuilder.append(i + 1 + ". " + igboApiResponse.getDefinitions().get(i) + "\n\n");

            holder.binding.definitions.setText(stringBuilder);
        }

//        ADJ - Adjective
//        ADV - Adverb
//        AV - Active verb
//        MV - Medial verb
//        PV - Passive verb
//        CJN - Conjunction


//        DEM - Demonstrative
//        NM - Name
//        NNC - Noun
//        NNP - Proper noun
//        CD - Number


//        PREP - Preposition
//        PRN - Pronoun
//        FW - Foreign word
//        QTF - Quantifier
//        WH - Interrogative

//        INTJ - Interjection
//        ISUF - Inflectional suffix
//        ESUF - Extensional suffix
//        SYM - Punctuations

        switch (igboApiResponse.getWordClass()) {
            case "ADJ":
                holder.binding.wordClass.setText("Adjective");
                break;
            case "ADV":
                holder.binding.wordClass.setText("Adverb");
                break;
            case "AV":
                holder.binding.wordClass.setText("Active verb");
                break;
            case "MV":
                holder.binding.wordClass.setText("Medial verb");
                break;
            case "PV":
                holder.binding.wordClass.setText("Passive verb");
                break;
            case "CJN":
                holder.binding.wordClass.setText("Conjunction");
                break;
            case "DEM":
                holder.binding.wordClass.setText("Demonstrative");
                break;
            case "NM":
                holder.binding.wordClass.setText("Name");
                break;
            case "NNC":
                holder.binding.wordClass.setText("Noun");
                break;
            case "NNP":
                holder.binding.wordClass.setText("Proper noun");
                break;
            case "CD":
                holder.binding.wordClass.setText("Number");
                break;
            case "PREP":
                holder.binding.wordClass.setText("Preposition");
                break;
            case "PRN":
                holder.binding.wordClass.setText("Pronoun");
                break;
            case "FW":
                holder.binding.wordClass.setText("Foreign word");
                break;
            case "QTF":
                holder.binding.wordClass.setText("Quantifier");
                break;
            case "WH":
                holder.binding.wordClass.setText("Interrogative");
                break;
            case "INTJ":
                holder.binding.wordClass.setText("Interjection");
                break;
            case "ISUF":
                holder.binding.wordClass.setText("Inflectional suffix");
                break;
            case "ESUF":
                holder.binding.wordClass.setText("Extensional suffix");
                break;
            case "SYM":
                holder.binding.wordClass.setText("Punctuations");
                break;
        }

        try {

            holder.binding.examples.setText("Igbo: " + igboApiResponse.getExamples().get(0).getIgbo() + "\n\n"
                    + "English: " + igboApiResponse.getExamples().get(0).getEnglish());

        } catch (Exception e) {

        }

        holder.binding.speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!igboApiResponse.getPronunciation().isEmpty()) {

//                    Thread thread = new Thread(new Runnable() {
//                        @Override
//                        public void run() {
                            MediaPlayer mediaPlayer = new MediaPlayer();
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            try {
                                mediaPlayer.setDataSource(igboApiResponse.getPronunciation());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                                Toast.makeText(context, "Playing pronunciation for " + igboApiResponse.getWord(), Toast.LENGTH_SHORT).show();

                            } catch (IOException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "Failed to play pronunciation for " + igboApiResponse.getWord(), Toast.LENGTH_SHORT).show();

                            }

//                        }
//                    });
//                    thread.start();


                } else {
                    Toast.makeText(context, "No pronunciation for " + igboApiResponse.getWord(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return igboApiResponseList.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder {

        ResponseItemBinding binding;

        public ResultViewHolder(@NonNull ResponseItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
