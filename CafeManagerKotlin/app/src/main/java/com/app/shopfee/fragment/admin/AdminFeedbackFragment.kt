package com.app.shopfee.fragment.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.shopfee.MyApplication
import com.app.shopfee.R
import com.app.shopfee.activity.AdminMainActivity
import com.app.shopfee.adapter.FeedbackAdapter
import com.app.shopfee.databinding.FragmentAdminFeedbackBinding
import com.app.shopfee.fragment.BaseFragment
import com.app.shopfee.model.Feedback
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList


class AdminFeedbackFragment : BaseFragment() {

    private var mFragmentAdminFeedbackBinding: FragmentAdminFeedbackBinding? = null
    private var mListFeedback: MutableList<Feedback>? = null
    private var mFeedbackAdapter: FeedbackAdapter? = null


    override fun initToolbar() {
        if (activity != null) {
            (activity as AdminMainActivity?)!!.setToolBar(getString(R.string.feedback))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mFragmentAdminFeedbackBinding = FragmentAdminFeedbackBinding.inflate(inflater, container, false)
        initView()
        getListFeedback()
        return mFragmentAdminFeedbackBinding!!.root
    }

    private fun initView() {
        if (activity == null) {
            return
        }
        val linearLayoutManager = LinearLayoutManager(activity)
        mFragmentAdminFeedbackBinding!!.rcvFeedback.layoutManager = linearLayoutManager
    }


    fun getListFeedback() {
        if (activity == null) {
            return
        }
        MyApplication[activity!!].getFeedbackDatabaseReference()
            ?.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (mListFeedback != null) {
                        mListFeedback!!.clear()
                    } else {
                        mListFeedback = ArrayList()
                    }
                    for (dataSnapshot in snapshot.children) {
                        val feedback = dataSnapshot.getValue(Feedback::class.java)
                        if (feedback != null) {
                            mListFeedback!!.add(0, feedback)
                        }
                    }
                    mFeedbackAdapter = FeedbackAdapter(mListFeedback)
                    mFragmentAdminFeedbackBinding!!.rcvFeedback.adapter = mFeedbackAdapter
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
}