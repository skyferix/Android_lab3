package elements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_lab3.R;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
        private ArrayList<Course> courseList;

        public recyclerAdapter(ArrayList<Course> courseList){
                this.courseList = courseList;
        }

        public class MyViewHolder extends  RecyclerView.ViewHolder{
                private TextView course;
                private TextView owner;
                private TextView startDate;
                private Button own, participate, moderate, all;

                public MyViewHolder(final View view){
                        super(view);
                        course = view.findViewById(R.id.course);
                        owner = view.findViewById(R.id.owner);
                        startDate = view.findViewById(R.id.startDate);
                }
        }

        @Override
        public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_cell,parent,false);
                return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                String name = courseList.get(position).getName();
                String owner = courseList.get(position).getOwner();
                String startData = courseList.get(position).getStartDate();
                holder.course.setText(name);
                holder.owner.setText(owner);
                holder.startDate.setText(startData);
        }

        @Override
        public int getItemCount() {
                return courseList.size();
        }

}
