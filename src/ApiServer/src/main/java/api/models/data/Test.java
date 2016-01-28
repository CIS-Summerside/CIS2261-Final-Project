package api.models.data;

import api.models.base.ApiBase;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Connor on 2016-01-27.
 */
@Entity
@Table(name = "test")
public class Test extends ApiBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "num")
    private Integer num;

    public Test(){

    }

    public Test(Integer num){
        this.num = num;
    }

    public Integer getId(){
        return this.id;
    }

    public Integer getNum(){
        return this.num;
    }
}
