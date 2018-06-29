package integral;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.zhy.autolayout.AutoLayoutActivity;
import java.util.ArrayList;
import java.util.List;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/10/13.
 */
public class terms_activity extends AutoLayoutActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.terms_layout);
        ListView lv_tk_id= (ListView) findViewById(R.id.lv_tk_id);
        ArrayList<String> arrayList=new ArrayList<>();//准备数据源
        arrayList.add("本平台致力维护一个诚信、互尊、民主的交流环境。凡发贴者，均须认同接受以下协议：");
        arrayList.add("1.本平台只提供一个信息交流平台，并有权对违反法律法规、违反本公司或本平台运营理念的信息进行限制性和筛选，有权对信息进行编辑、删除，有权对发布人员进行警示、账号封存、禁止登录等限制性措施。");
        arrayList.add("2.所有作品的著作权均归原作者享有，请您和我们一样尊重他人的著作权等合法权益。");
        arrayList.add("3.所有作品仅供您个人学习、研究或欣赏，不得用于商业或者其他用途，否则，一切后果均由您自己承担，我们对此不承担任何法律责任。");
        arrayList.add("4.您应当对刊载的所有信息资料的可靠性做出判断和评估，所有风险和责任由您独自承担。任何情况下，因使用或者信赖上述作品或者信息而可能遭致的意外、疏忽、侵权及其造成的一切直接或者间接的损失（包括因下载而感染电脑病毒），我们均不承担任何法律责任。");
        arrayList.add("5.对可能因第三方原因造成的互联网软硬件设备故障或失灵、或人为操作疏忽而全部或部分中断、延迟、遗漏误导或造成资料传输或储存上的错误,我们均不承担任何责任。");
        arrayList.add("6.本平台可能因黑客攻击、计算机病毒侵入或发作、政府管制而造成的暂时性关闭，或因前述原因以及与本频道链接的其它网站原因导致个人资料泄露、丢失、被盗用或被篡改等，本平台不承担任何责任。");
        arrayList.add("7.禁止制作、复制、发布和传播具有反动、淫秽、色情、暴力、凶杀等内容的信息，一经发现立即删除。若您因此触犯法律，一切后果自负，我们对此不承担任何责任。");
        arrayList.add("8.如涉及侵犯版权等问题，请您及时通知我们，并出示身份证明、著作权权属证明及侵权情况证明，我们将立即采取措施予以解决。");
        arrayList.add("9.用户应妥善保管频道账户及密码信息，如用户将密码告知他人或与他人共享同一ID，从而导致任何资料泄露等损失，由用户自行承担。");
        arrayList.add("10.以任何方式登录手机app或网站，直接、间接使用上述信息资料，我们均视为您自愿接受并完全同意本声明。");
        arrayList.add("本公司对以上条款保留解释、修改权。");
        setColorAdapter simpleAdapter=new setColorAdapter(arrayList);
        lv_tk_id.setAdapter(simpleAdapter);
        findViewById(R.id.jfrule_back_id).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jfrule_back_id:
                finish();
                break;
        }
    }
    class setColorAdapter extends BaseAdapter {
        List<String> mdata;
        public setColorAdapter( List<String> mdata){
            this.mdata=mdata;
        }
        @Override
        public int getCount() {
            return mdata.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LinearLayout.inflate(getBaseContext(),R.layout.tk_item, null);
            }
            TextView textView = (TextView) convertView.findViewById(R.id.tv_tk_id);
            textView.setText(mdata.get(position));
            if(position==0||position==11){
                textView.setTextColor(getResources().getColor(R.color.black));
            }else{
                textView.setTextColor(getResources().getColor(R.color.gray));
            }
            return convertView;
        }
    }
    }
