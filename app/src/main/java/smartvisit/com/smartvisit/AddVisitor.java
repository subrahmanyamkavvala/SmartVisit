package smartvisit.com.smartvisit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import smartvisit.com.smartvisit.db.VisitorsCheckIn;
import smartvisit.com.smartvisit.utils.Utils;
import smartvisit.com.smartvisit.utils.VisitorAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddVisitor.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AddVisitor extends Fragment {
    private static final  String TAG = "AddVisitor";

    private ArrayList<VisitorsCheckIn> mVisitorsList = null;
    private VisitorAdapter mAdapter = null;
    private ListView mListView;


    public AddVisitor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_addvisitor, container, false);

        mListView = (ListView)view.findViewById(R.id.visitor_list);
        mVisitorsList = new ArrayList<>();


        mAdapter = new VisitorAdapter(getActivity(),mVisitorsList);
        mListView.setAdapter(mAdapter);


       // insertVisits();
        updateVisitorList();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.menu_visitor, menu);
       // return true;
       // super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_addVisitor:
                Log.d(TAG,"action_addVisitor");
                Intent intent = new Intent(getActivity(),Appoinment.class);
                //startActivity(intent);
                startActivityForResult(intent,200);
                return true;
            default:
                break;
        }

        return false;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void insertVisits() {


        try {
            VisitorsCheckIn checkIn = new VisitorsCheckIn();

            for (int index = 0;index<10;index++){


                checkIn.vis_fullname = "Visior "+(index+5);
                checkIn.vis_company = "infosys  "+(index+5);
                checkIn.vis_email = "test@gmail.co, ";
                checkIn.vis_tomeet = "To meet "+(index+5);
                checkIn.vis_mobile = "92424827"+(index+5);
                checkIn.vis_indate = new Date();
                checkIn.vis_indate = new Date();

                Dao<VisitorsCheckIn,Integer> dao =AppController.getInstance().getHelper().getVisitorsDao();
                dao.create(checkIn);

            }


        }catch (SQLException e){

        }


    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        updateVisitorList();

    }

    private void updateVisitorList(){

        List<VisitorsCheckIn> l = Utils.getVisitorsList();
        if(l!=null){

            Log.d(TAG, "records "+l.size());

            mVisitorsList.clear();
            mVisitorsList.addAll(l);

             mAdapter.notifyDataSetChanged();

            //mListView.setAdapter(mAdapter);
        }else {
            Log.d(TAG,"list is nulll ");

        }
    }


}
