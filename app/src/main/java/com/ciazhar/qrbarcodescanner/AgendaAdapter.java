package com.ciazhar.qrbarcodescanner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ciazhar.qrbarcodescanner.model.Agenda;

import java.util.List;

/**
 * Created by ciazhar on 21/12/17.
 */

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.ViewHolder>{

    private List<Agenda> agendaList;
    private Context context;

    public AgendaAdapter(List<Agenda> agendaList, Context context) {
        this.agendaList = agendaList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_recycler_agenda,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Agenda agenda = agendaList.get(position);

        holder.agendaName.setText(agenda.getAgendaName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DialogActivity.class);
                intent.putExtra("agenda.name",agenda.getAgendaName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return agendaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView agendaName;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            agendaName = itemView.findViewById(R.id.agenda_name);
            cardView = itemView.findViewById(R.id.agenda_cv);
        }
    }
}
