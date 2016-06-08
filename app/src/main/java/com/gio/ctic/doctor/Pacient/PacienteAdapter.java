package com.gio.ctic.doctor.Pacient;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gio.ctic.doctor.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by giovanny on 08/06/16.
 */
public class PacienteAdapter extends RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder>{

    private ArrayList<Paciente> mPacienteSet;
    private static MyClickListener myClickListener;

    public static class PacienteViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        TextView tid;
        TextView tnombre;
        TextView tdni;

        public PacienteViewHolder(View itemView) {
            super(itemView);
            tid=(TextView)itemView.findViewById(R.id.textView);
            tnombre =(TextView)itemView.findViewById(R.id.textView2);
            tdni =(TextView)itemView.findViewById(R.id.textView3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public PacienteAdapter(ArrayList<Paciente> mPacienteSet) {
        this.mPacienteSet = mPacienteSet;
        Ordenar();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public void Ordenar(){
        // Sorting
        Collections.sort(mPacienteSet, new Comparator<Paciente>() {
            @Override
            public int compare(Paciente lhs, Paciente rhs) {
                return lhs.getId().compareTo(rhs.getId());
            }
        });

        notifyDataSetChanged();
    }

    @Override
    public PacienteAdapter.PacienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_paciente, parent, false);

        return new PacienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PacienteAdapter.PacienteViewHolder holder, int position) {
        holder.tid.setText(mPacienteSet.get(position).getId());
        holder.tdni.setText(mPacienteSet.get(position).getDni());
        holder.tnombre.setText(mPacienteSet.get(position).getNombre());

    }

    @Override
    public int getItemCount() {
        return mPacienteSet.size();
    }


    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

}
