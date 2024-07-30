<template>
  <div class="main-content">
    <div style="display: flex; align-items: flex-start; grid-gap: 10px">

      <div style="flex: 1;">
        <inter-list :categoryName="current" ref="interListRef" />
        <Footer />
      </div>

      <div style="width: 260px">
        <div class="card" style="margin-bottom: 10px">
          <div style="font-size: 20px; font-weight: bold; margin-bottom: 10px">欢迎您！😊</div>
          <a href="/front/person"><div style="color: #666">查看内推资料，快人一步！</div></a>
        </div>

        <div class="card" style="margin-bottom: 10px">
          <div style="display: flex; align-items: center; padding-bottom: 10px; border-bottom: 1px solid #ddd">
            <div style="font-size: 20px; flex: 1">内推访问量榜单</div>
            <div style="font-size: 12px; color: #666; cursor: pointer;" @click="refreshTop"><i class="el-icon-refresh"></i> 换一换</div>
          </div>
          <div>
            <div v-for="item in showList" :key="item.id" style="margin: 15px 0" class="line1">
              <a :href="'/front/interDetail?interId=' + item.id" target="_blank">
                <span style="width: 18px; display: inline-block; text-align: right; margin-right: 10px">
                  <span style="color: orangered" v-if="item.index === 1">{{ item.index }}</span>
                  <span style="color: goldenrod" v-else-if="item.index === 2">{{ item.index }}</span>
                  <span style="color: dodgerblue" v-else-if="item.index === 3">{{ item.index }}</span>
                  <span style="color: #666" v-else>{{ item.index }}</span>
                </span>
                <span style="color: #666;">{{ item.title }}</span>
              </a>
            </div>
          </div>
        </div>

        <div style="margin-bottom: 10px">
          <div v-for="item in topActivityList" :key="item.id" style="margin-bottom: 10px">
            <a :href="'/front/activityDetail?activityId=' + item.id" target="_blank"><img :src="item.cover" alt="" style="width: 100%; border-radius: 5px"></a>
          </div>
        </div>
        
      </div>
    </div>
  </div>
</template>

<script>

import Footer from "@/components/Footer";
import BlogList from "@/components/BlogList";
export default {
  components: {
    BlogList,
    Footer
  },
  data() {
    return {
      current: '全部内推',  //当前选中的分类名称
      categoryList: [],

      topList: [],
      showList: [],
      lastIndex: 0,
      topActivityList: []
    }
  },
  mounted() {
    // this.load()

    this.refreshTop()

    this.loadTopActivity()

  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadTopActivity() {
      this.$request.get('/activity/selectTop').then(res => {
        this.topActivityList = res.data || []
      })
    },
    refreshTop() {
        // 发起 GET 请求获取顶部列表数据
      this.$request.get('/internalreferral/selectTop').then(res => {
         // 将返回的数据赋值给 topList，如果没有数据则默认为空数组
        this.topList = res.data || []
         // 初始化一个索引计数器 i，并为 topList 中的每个项添加 index 属性，从 1 开始递增
        let i = 1
        this.topList.forEach(item => item.index = i++)

        // 0  5  0
         // 如果 lastIndex 等于 20，则重置为 0
        if (this.lastIndex === 20) {
          this.lastIndex = 0
        }
         // 根据 lastIndex，从 topList 中截取出一个长度为 5 的子数组，并赋值给 showList
        this.showList = this.topList.slice(this.lastIndex, this.lastIndex+5)  // 0-5   5- 10  // 0-5
         // 更新 lastIndex，增加 5
        this.lastIndex += 5  // 5  10  5
      })
    },
    selectCategory(categoryName) {
      this.current = categoryName
    },
    // load() {
    //   // 请求分类的数据
    //   this.$request.get('/category/selectAll').then(res => {
    //     this.categoryList = res.data || []
    //     this.categoryList.unshift({ name: '全部博客' })
    //   })
    // },
  }
}
</script>

<style>
.category-item {
  text-align: center;
  padding: 10px 0;
  font-size: 16px;
  cursor: pointer;
}
.category-item-active {
  background-color: #1890ff;
  color: #fff;
  border-radius: 5px;
}


</style>
