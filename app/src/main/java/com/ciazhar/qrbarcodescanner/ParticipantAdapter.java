package com.ciazhar.qrbarcodescanner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ciazhar.qrbarcodescanner.model.Participant;

import java.util.List;

/**
 * Created by ciazhar on 20/12/17.
 */

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ViewHolder>{

    private List<Participant> participantList;
    private Context context;

    public ParticipantAdapter(List<Participant> participantList, Context context) {
        this.participantList = participantList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_recycler_participant,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Participant participant = participantList.get(position);

        holder.participantName.setText(participant.getName());
        holder.participantEmail.setText(participant.getEmail());
        holder.participantFlag.setText(participant.getAttendanceStatus()?"Datang":"Tidak");

    }

    @Override
    public int getItemCount() {
        return participantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView participantName, participantEmail, participantFlag;

        public ViewHolder(View itemView) {
            super(itemView);
            participantName = itemView.findViewById(R.id.participant_name);
            participantEmail = itemView.findViewById(R.id.participant_email);
            participantFlag = itemView.findViewById(R.id.participant_flag);
        }
    }
}
