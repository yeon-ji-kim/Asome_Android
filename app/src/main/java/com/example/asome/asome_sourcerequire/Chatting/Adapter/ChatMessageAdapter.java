package com.example.asome.asome_sourcerequire.Chatting.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asome.asome_sourcerequire.Chatting.Model.Chat;
import com.example.asome.asome_sourcerequire.Chatting.Model.SectionDataModel;
import com.example.asome.asome_sourcerequire.Chatting.Model.SingleItemModel;
import com.example.asome.asome_sourcerequire.R;

import java.util.ArrayList;

import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_ALARM;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_DONE;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_SCHEDULE_MY;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_SCHEDULE_OTHER;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_START;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_TEXT;


/**
 * [OUTLINE]
 * 채팅 액티비티의 메시지 리스트 어댑터와 뷰홀더
 */
public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageViewHolder> {

    //오른쪽은 내 메시지, 왼쪽은 상대 메시지
    private static final int RIGHT = 0, LEFT = 1;

    private ArrayList<Chat> chats;

    public ChatMessageAdapter(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    @Override
    public int getItemViewType(int position) {

        //오른쪽은 내 메시지, 왼쪽은 상대 메시지
        if (chats.get(position).getIs_me())
            return RIGHT;
        else
            return LEFT;

    }

    @Override
    public ChatMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (viewType) {

            //내 메시지일 경우 뷰는 item_message_right
            case RIGHT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_right, parent, false);
                break;

            //상대 메시지일 경우 뷰는 item_message_left
            case LEFT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_left, parent, false);
                break;
        }

        return new ChatMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatMessageViewHolder holder, int position) {
        Chat chat = chats.get(position);
        holder.bind(chat);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }


}


class ChatMessageViewHolder extends RecyclerView.ViewHolder {


    //cf) 메시지 종류는 3가지 {텍스트, 이미지, 맵} : 종류를 명시하지 않고 메시지라고 칭한 것은 세 타입 모두

    TextView tv_user_real_name;//한글로 메시지를 보낸 사람의 이름을 쓰는 텍스트뷰
    TextView txtMessage;//텍스트 메시지 내용을 보여주는 텍스트뷰
    //ImageView txtMessage_img;//이미지 메시지의 이미지를 보여주는 이미지뷰
    TextView txtTime;//텍스트 메시지를 보낸 시간을 보여주는 텍스트뷰
   // TextView txtCheck;//상대방이 메시지를 읽었는지 보여주는 텍스트뷰
   // TextView txtTime_img;//이미지 메시지를 보낸 시간을 보여주는 텍스트뷰
    LinearLayout container_txt;//텍스트 메시지의 레이아웃
    //LinearLayout container_img;//이미지 메시지의 레이아웃
    TextView dateLine;//날짜 변경선을 보여주는 텍스트뷰
    Context context;
    RecyclerView rv_choice_card;

    public ChatMessageViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();

        //txtCheck = (TextView) itemView.findViewById(R.id.read_check);
        txtMessage = (TextView) itemView.findViewById(R.id.tv_message);
        //  txtMessage_img = (ImageView) itemView.findViewById(R.id.message_img);
        //  txtTime_img = (TextView) itemView.findViewById(R.id.time_img);
        txtTime = (TextView) itemView.findViewById(R.id.timestamp);
        //  container_img = (LinearLayout) itemView.findViewById(R.id.message_img_set);
        container_txt = (LinearLayout) itemView.findViewById(R.id.msg_container);
        dateLine = (TextView) itemView.findViewById(R.id.dateLine);
        tv_user_real_name = (TextView) itemView.findViewById(R.id.tv_user_real_name);
        rv_choice_card = (RecyclerView)itemView.findViewById(R.id.rv_choice_card);
    }


    /**
     * 들어오는 메시지의 종류마다 어떻게 화면에 출력할 지 정한다.
     * 1. 텍스트
     * 2. 이미지
     * 3. 내이미지
     * 4. 맵
     * 5. 날짜선
     */
    public void bind(final Chat chat) {

        //메시지를 보낸 사람이름을 세팅
        tv_user_real_name.setText(chat.getUser_no());

        //메시지의 읽음 처리
        //만약 읽음 처리가 필요없다면(날짜선) View.GONE 처리됨
   /*     if (chat.getRead_check().equals(TAG_READ)) {
            txtCheck.setText("읽음");
        } else {
            txtCheck.setText("안읽음");
        }*/

        //케이스 마다 안쓰는 뷰 -> View.GONE
        //쓰는 뷰-->View.VISIBLE
        switch (chat.getAction()) {

            //텍스트 메시지 케이스
            //쓰는 뷰
            //안쓰는 뷰
            case  ACTION_START:
                ArrayList<SectionDataModel> array_action_start = new ArrayList<SectionDataModel>();
                ArrayList<SingleItemModel> ACTION_STARTsingleItem = new ArrayList<SingleItemModel>();
                SectionDataModel ACTION_STARTdm = new SectionDataModel();


                /*for (int j = 0; j <= 10; j++) {
                    ACTION_STARTsingleItem.add(new SingleItemModel("아템 ", "유알엘 " + j));
                }
*/
                container_txt.setVisibility(View.VISIBLE);
                txtMessage.setVisibility(View.VISIBLE);
                txtTime.setVisibility(View.VISIBLE);
                txtMessage.setText(chat.getMessage());
                txtTime.setText(chat.getTimestamp());
                dateLine.setVisibility(View.GONE);

                rv_choice_card.setVisibility(View.VISIBLE);
                ACTION_STARTsingleItem.add(new SingleItemModel("내 스케줄 알려줘", ACTION_SCHEDULE_MY ));
                ACTION_STARTsingleItem.add(new SingleItemModel("오늘 알람을 설정해야겠어", ACTION_ALARM ));
                ACTION_STARTsingleItem.add(new SingleItemModel("오늘 스케줄 끝났어", ACTION_DONE ));
                ACTION_STARTsingleItem.add(new SingleItemModel("팀원 스케줄 보여줘", ACTION_SCHEDULE_OTHER ));
                ACTION_STARTsingleItem.add(new SingleItemModel("에러가 났어", "err" ));
                ACTION_STARTsingleItem.add(new SingleItemModel("메뉴 보여줘", ACTION_START ));

                ACTION_STARTdm.setAllItemsInSection(ACTION_STARTsingleItem);
                array_action_start.add(ACTION_STARTdm);

                rv_choice_card.setHasFixedSize(true);
                RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(context, array_action_start);
                rv_choice_card.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                rv_choice_card.setAdapter(adapter);
                break;


/*
            case ACTION_SCHEDULE_OTHER:
                container_txt.setVisibility(View.VISIBLE);
                txtMessage.setVisibility(View.VISIBLE);
                txtTime.setVisibility(View.VISIBLE);
                txtMessage.setText(chat.getMessage());
                txtTime.setText(chat.getTimestamp());
                dateLine.setVisibility(View.GONE);

                rv_choice_card.setVisibility(View.VISIBLE);
                ArrayList<SectionDataModel> allSampleData = new ArrayList<SectionDataModel>();
                SectionDataModel dm = new SectionDataModel();
                ArrayList<SingleItemModel> al_schedule_other = new ArrayList<SingleItemModel>();
*//*                al_schedule_other.add(new SingleItemModel("내 스케줄 알려줘", "유알엘 " ));
                al_schedule_other.add(new SingleItemModel("오늘 알람을 설정해야겠어", "유알엘 " ));
                al_schedule_other.add(new SingleItemModel("오늘 스케줄 끝났어", "유알엘 " ));
                al_schedule_other.add(new SingleItemModel("팀원 스케줄 보여줘", "유알엘 " ));
                al_schedule_other.add(new SingleItemModel("에러가 났어", "유알엘 " ));
                al_schedule_other.add(new SingleItemModel("잡담하자", "유알엘 " ));*//*
                dm.setAllItemsInSection(al_schedule_other);
                allSampleData.add(dm);
                rv_choice_card.setHasFixedSize(true);
                RecyclerViewDataAdapter adapter2 = new RecyclerViewDataAdapter(context, allSampleData);
                rv_choice_card.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                rv_choice_card.setAdapter(adapter2);
                break;*/
             case ACTION_TEXT:

                // container_img.setVisibility(View.GONE);
                container_txt.setVisibility(View.VISIBLE);
                //           txtCheck.setVisibility(View.VISIBLE);
                txtMessage.setVisibility(View.VISIBLE);
                txtTime.setVisibility(View.VISIBLE);
                txtMessage.setText(chat.getMessage());
                txtTime.setText(chat.getTimestamp());
                dateLine.setVisibility(View.GONE);


                break;
            case ACTION_DONE:

                // container_img.setVisibility(View.GONE);
                container_txt.setVisibility(View.VISIBLE);
                //           txtCheck.setVisibility(View.VISIBLE);
                txtMessage.setVisibility(View.VISIBLE);
                txtTime.setVisibility(View.VISIBLE);
                txtMessage.setText(chat.getMessage());
                txtTime.setText(chat.getTimestamp());
                dateLine.setVisibility(View.GONE);


                break;

            case ACTION_SCHEDULE_OTHER:


                // container_img.setVisibility(View.GONE);
                container_txt.setVisibility(View.VISIBLE);
                //           txtCheck.setVisibility(View.VISIBLE);
                txtMessage.setVisibility(View.VISIBLE);
                txtTime.setVisibility(View.VISIBLE);
                txtMessage.setText(chat.getMessage());
                txtTime.setText(chat.getTimestamp());
                dateLine.setVisibility(View.GONE);


                break;

            case ACTION_SCHEDULE_MY:


                // container_img.setVisibility(View.GONE);
                container_txt.setVisibility(View.VISIBLE);
                //           txtCheck.setVisibility(View.VISIBLE);
                txtMessage.setVisibility(View.VISIBLE);
                txtTime.setVisibility(View.VISIBLE);
                txtMessage.setText(chat.getMessage());
                txtTime.setText(chat.getTimestamp());
                dateLine.setVisibility(View.GONE);


                break;


            //날짜선 케이스
            //쓰는 뷰
            //안쓰는 뷰
            case "dateLine":
          //      container_img.setVisibility(View.GONE);
                container_txt.setVisibility(View.GONE);
       //         txtCheck.setVisibility(View.GONE);
                txtMessage.setVisibility(View.GONE);
                txtTime.setVisibility(View.GONE);
                dateLine.setVisibility(View.VISIBLE);
                dateLine.setText(chat.getMessage());
                break;

        }

    }



}
