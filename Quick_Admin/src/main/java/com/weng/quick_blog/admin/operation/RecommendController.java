package com.weng.quick_blog.admin.operation;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.common.request.recommend.QueryRecommendListPageRequest;
import com.weng.quick_blog.common.request.recommend.UpdateRecommendRequest;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.operation.Recommend;
import com.weng.quick_blog.entity.operation.vo.RecommendVO;
import com.weng.quick_blog.service.operation.RecommendService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 翁丞健
 * @Date 2021/12/27 16:13
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/admin/operation/recommend")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;


    @GetMapping("/list")
    public Result<PageQuery<Recommend>> list(QueryRecommendListPageRequest request){
        PageQuery<Recommend> result = recommendService.queryPage(request);

        return Result.Success(result);
    }

    @GetMapping("/info/{id}")
    public Result<RecommendVO> info(@PathVariable("id") Integer id){
        RecommendVO result = recommendService.findVOById(id);

        return Result.Success(result);
    }

    @PostMapping("/save")
    public Result save(Recommend recommend){
        recommendService.save(recommend);

        return Result.Success();
    }

    /**
     * 目前推荐接口只能修改是否置顶
     * @param request
     * @return
     */
    @PutMapping("/update")
    public Result update(UpdateRecommendRequest request){

        Recommend recommend = new Recommend();

        BeanUtils.copyProperties(request,recommend);

        recommendService.updateById(recommend);

        return Result.Success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody  Integer[] ids){
         recommendService.deleteBatch(ids);

        return Result.Success();
    }


}
