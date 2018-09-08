package in.co.chicmic.flyoverairways.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import in.co.chicmic.flyoverairways.R;
import in.co.chicmic.flyoverairways.listeners.CancelFlightClickListener;

public class CancelFightRecyclerAdapter
        extends RecyclerView.Adapter<CancelFightRecyclerAdapter.ViewHolder>{
    private List<Integer> mSeatStatus = new ArrayList<>();
    private List<Integer> mPassengerCode = new ArrayList<>();
    private CancelFlightClickListener mListener;

    public CancelFightRecyclerAdapter(List<Integer> pSeatStatus, CancelFlightClickListener pListener){
        mListener = pListener;
        for (int i = 0; i < pSeatStatus.size() ; i++){
            if (pSeatStatus.get(i) != -1) {
                mPassengerCode.add(pSeatStatus.get(i));
                mSeatStatus.add(i);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cancel_flight_recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        viewHolder.mPassCodeTV.setText(String.valueOf(mPassengerCode.get(position)));
        viewHolder.mNumOfSeatsTV.setText(String.format(Locale.getDefault(), "Seat No: %d", mSeatStatus.get(position)));
        viewHolder.mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCancelClick(viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSeatStatus.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNumOfSeatsTV;
        TextView mPassCodeTV;
        Button mCancelButton;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNumOfSeatsTV = itemView.findViewById(R.id.tv_seats);
            mPassCodeTV = itemView.findViewById(R.id.tv_pass_code);
            mCancelButton = itemView.findViewById(R.id.btn_cancel);
        }
    }
}
