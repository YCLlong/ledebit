import com.ledebit.dao.debitMapper;
import com.ledebit.pojo.debit;
import com.ledebit.util.MD5Util;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/12/8.
 */
public class debittest {

//    @Autowired
//    private debitMapper debitMapper;
//    @Test
//    public void test(){
//        debit debitinfo=debitMapper.selectByPrimaryKey(111);
//        int dun=debitinfo.getAge();
//        System.out.print(dun);
//
//    }
    public static void main(String[] args){
        String password="lele";
       String s= MD5Util.MD5EncodeUtf8(password);
        System.out.print(s);
    }
}
