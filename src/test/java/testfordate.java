import com.ledebit.dao.loanMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/8.
 */
public class testfordate {
    @Autowired
    private loanMapper loanMappers;
    @Test
    public void Test() throws ParseException {
        Loantest loantest=new Loantest();
//        loan loan=loantest.searchloan();
//        Long day= DatetimeUtil.datetolong(loan.getCrDeadline());
//        System.out.println("贷款期限是："+day);



        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-01");
        Date date=new Date();
        Long dates=date.getTime();
//        Long dayout=day-DatetimeUtil.datetolong(date);
        System.out.println("剩下是："+format.format(dates));
        Date date2=format.parse("2017-09-01");

        long day=(date.getTime()-date2.getTime())/(24*60*60*1000);
        System.out.println("相隔有"+day);
        System.exit(0);

    }



}
