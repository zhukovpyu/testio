package com.example.android.testio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Конфигурация ресайклер вью
public class RecyclerView_Config {
    private Context mContext;
    private PassedTestsAdapter mPassedTestsAdapter;
    private MadeTestsAdapter mMadeTestsAdapter;
    private TrysAdapter mTrysAdapter;
    private QuestionAdapter mQuestionAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<PassedTestObject> passedTestsObjects, List<String> keys){
        mContext = context;
        mPassedTestsAdapter = new PassedTestsAdapter(passedTestsObjects,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mPassedTestsAdapter);
    }

    public void setConfig2(RecyclerView recyclerView, Context context, List<MadeTestsObject>madeTestsObjects, List<String> keys){
        mContext = context;
        mMadeTestsAdapter = new MadeTestsAdapter(madeTestsObjects,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mMadeTestsAdapter);
    }

    public void setConfig3(RecyclerView recyclerView, Context context, List<TrysObject> trysObjects, List<String> keys){
        mContext = context;
        mTrysAdapter = new TrysAdapter(trysObjects,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mTrysAdapter);
    }

    public void setConfig4(RecyclerView recyclerView, Context context, List<QuestionsObject> questionsObjects, List<String> keys){
        mContext = context;
        mQuestionAdapter = new QuestionAdapter(questionsObjects,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mQuestionAdapter);
    }

    class MadeTestItemView extends RecyclerView.ViewHolder{
        private TextView mNameOfTest;
        private TextView mIdOfTest;

        private String key;

        public MadeTestItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.madetests_list_item,parent,false));

            mNameOfTest =(TextView) itemView.findViewById(R.id.tryer_Txt2);
            mIdOfTest =(TextView) itemView.findViewById(R.id.point_Txt2);
        }

        public void bind2(MadeTestsObject madeTest, String key){
            mNameOfTest.setText(madeTest.getNameOfTest());
            mIdOfTest.setText(madeTest.getTestId());
            this.key = key;
        }
    }

    class TrysItemView extends RecyclerView.ViewHolder{
        private TextView mTryer;
        private TextView mPoints;

        private String key;

        public TrysItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.trys_list_item,parent,false));

            mTryer =(TextView) itemView.findViewById(R.id.tryer_Txt2);
            mPoints =(TextView) itemView.findViewById(R.id.point_Txt2);
        }

        public void bind2(TrysObject trysObject, String key){
            mTryer.setText(trysObject.getPasser());
            mPoints.setText(trysObject.getScore());
            this.key = key;
        }
    }

    class QuestionItemView extends RecyclerView.ViewHolder{
        private TextView mQText;
        private TextView mQAnswer;
        private TextView mScore;

        private String key;

        public QuestionItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.question_list_item,parent,false));

            mQText =(TextView) itemView.findViewById(R.id.QTxt);
            mQAnswer =(TextView) itemView.findViewById(R.id.AnsTxt);
            mScore =(TextView) itemView.findViewById(R.id.score_for_answ);
        }

        public void bind(QuestionsObject questionsObject, String key){
            mQText.setText(questionsObject.getQuestionText());
            mQAnswer.setText(questionsObject.getAnswer());
            mScore.setText(questionsObject.getAmountOfPoints());
            this.key = key;
        }

    }

    class PassedTestItemView extends RecyclerView.ViewHolder{
        private TextView mNameOfTest;
        private TextView mIdOfTest;
        private TextView mTotalScore;

        private String key;

        public PassedTestItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
            inflate(R.layout.passedtests_list_item,parent,false));

            mNameOfTest =(TextView) itemView.findViewById(R.id.QTxt);
            mIdOfTest =(TextView) itemView.findViewById(R.id.AnsTxt);
            mTotalScore =(TextView) itemView.findViewById(R.id.score_for_answ);
        }

        public void bind(PassedTestObject passedTest, String key){
            mNameOfTest.setText(passedTest.getNameOfTest());
            mIdOfTest.setText(passedTest.getTestId());
            mTotalScore.setText(passedTest.getFinalScore());
            this.key = key;
        }

    }
    class PassedTestsAdapter extends RecyclerView.Adapter<PassedTestItemView> {

        private List<PassedTestObject> mPassedTestsList;
        private List<String> mKeys;



        public PassedTestsAdapter(List<PassedTestObject> mPassedTestsList, List<String> mKeys) {
            this.mPassedTestsList = mPassedTestsList;
            this.mKeys = mKeys;
        }

        @Override
        public PassedTestItemView onCreateViewHolder( ViewGroup parent, int ViewType) {
            return new PassedTestItemView(parent);
        }

        @Override
        public void onBindViewHolder(final PassedTestItemView holder, int position) {
            holder.bind(mPassedTestsList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mPassedTestsList.size();
        }

    }

    class MadeTestsAdapter extends  RecyclerView.Adapter<MadeTestItemView>{

        private List<MadeTestsObject> mMadeTestsList;
        private List<String> mKeys;

        public MadeTestsAdapter(List<MadeTestsObject> mMadeTestsList, List<String> mKeys) {
            this.mMadeTestsList = mMadeTestsList;
            this.mKeys = mKeys;
        }

        @Override
        public MadeTestItemView onCreateViewHolder( ViewGroup parent, int ViewType) {
            return new MadeTestItemView(parent);
        }

        @Override
        public void onBindViewHolder(final MadeTestItemView holder, int position) {
            //вот это нада
            final MadeTestsObject madeTestsItem = (MadeTestsObject) mMadeTestsList.get(position);
            holder.bind2(mMadeTestsList.get(position),mKeys.get(position));
            //и вот это нада
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent intentx = new Intent(mContext,TestInformationActivity.class);
                intentx.putExtra("TestId",madeTestsItem.getTestId());
                mContext.startActivity(intentx);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mMadeTestsList.size();
        }

    }

    class TrysAdapter extends  RecyclerView.Adapter<TrysItemView>{

        private List<TrysObject> mTrys;
        private List<String> mKeys;

        public TrysAdapter(List<TrysObject> mTrys, List<String> mKeys) {
            this.mTrys = mTrys;
            this.mKeys = mKeys;
        }

        @Override
        public TrysItemView onCreateViewHolder( ViewGroup parent, int ViewType) {
            return new TrysItemView(parent);
        }

        @Override
        public void onBindViewHolder(final TrysItemView holder, int position) {
            holder.bind2(mTrys.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mTrys.size();
        }
    }

    class QuestionAdapter extends  RecyclerView.Adapter<QuestionItemView>{

        private List<QuestionsObject> mQuestions;
        private List<String> mKeys;

        public QuestionAdapter(List<QuestionsObject> mQuestionsx, List<String> mKeys) {
            this.mQuestions = mQuestionsx;
            this.mKeys = mKeys;
        }

        @Override
        public QuestionItemView onCreateViewHolder( ViewGroup parent, int ViewType) {
            return new QuestionItemView(parent);
        }

        @Override
        public void onBindViewHolder(final QuestionItemView holder, int position) {
            holder.bind(mQuestions.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mQuestions.size();
        }
    }

}
