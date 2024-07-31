<template>
  <div class="main-content">
    <div style="display: flex; grid-gap: 10px">

      <div style="flex: 1; width: 0">
        <div class="card" style="padding: 30px; margin-bottom: 10px">
          <div style="font-weight: bold; font-size: 24px; margin-bottom: 20px">{{ internalreferral.title }}</div>
          <div style="color: #666; margin-bottom: 20px">
            <span style="margin-right: 20px"><i class="el-icon-date"> 开 始 时 间 :</i> {{ internalreferral.start }}</span>
            <span style="margin-right: 20px"><i class="el-icon-date"> 结 束 时 间 :</i> {{ internalreferral.end }}</span>
            <span style="margin-right: 20px"><i class="el-icon-eye"></i> {{ internalreferral.readCount }}</span>
          </div>

          <div class="w-e-text">
            <div v-html="internalreferral.content"></div>
          </div>

          <div style="color: #666; margin-bottom: 20px">
            <span style="margin-right: 20px"><i class="el-icon-key"> 内 推 码 :</i> {{ internalreferral.irCode }}</span>
            <span style="margin-right: 20px"><i class="el-icon-phone"> 主办方联系方式 :</i> {{ internalreferral.contact }}</span>
          </div>

        </div>
        <Comment :fid="interId" module='内推' />
      </div>

    </div>

    <Footer />
  </div>
</template>

<script>
import Footer from "@/components/Footer";
import Comment from "@/components/Comment";

export default {
  name: "InterDetail",
  components: {
    Comment,
    Footer
  },
  data() {
    return {
      interId: this.$route.query.interId,
      internalreferral: {},
      tagsArr: [],
      recommendList: [],
    }
  },
  created() {
    this.load()
    this.$request.put('/internalReferral/updateReadCount/' + this.interId)
  },
  methods: {
    /** 
     * setLikes() {
          this.$request.post('/likes/set', {  fid: this.interId, module: '内推' }).then(res => {
            if (res.code === '200') {
              this.$message.success('操作成功')
    
              this.load()  // 重新加载数据
            }
          })
        },
        setCollect() {
          this.$request.post('/collect/set', {  fid: this.interId, module: '内推' }).then(res => {
            if (res.code === '200') {
              this.$message.success('操作成功')
    
              this.load()  // 重新加载数据
            }
          })
        },
    */

    load() {
      this.$request.get('/internalReferral/selectById/' + this.interId).then(res => {
        this.internalreferral = res.data || {}
      })

      //this.$request.get('/internalReferral/selectRecommend/' + this.interId).then(res => {
      //  this.recommendList = res.data || []
      //})
    }
  }
}
</script>

<style>
/* blockquote 样式 */
blockquote {
  display: block;
  border-left: 8px solid #d0e5f2;
  padding: 20px 10px;
  margin: 10px 0;
  line-height: 1.4;
  font-size: 100%;
  background-color: #f1f1f1;
}

/* code 样式 */
code {
  display: inline-block;
  *display: inline;
  *zoom: 1;
  background-color: #f1f1f1;
  border-radius: 3px;
  padding: 3px 5px;
  margin: 0 3px;
}

pre code {
  display: block;
}

p {
  line-height: 30px
}

.active {
  color: orange !important;
}

.recommend-title {
  margin-bottom: 5px;
}

.recommend-title:hover {
  color: #2a60c9;
}

.comment-active {
  color: #2a60c9;
}

pre {
  white-space: pre-wrap;
  /*css-3*/
  white-space: -moz-pre-wrap;
  /*Mozilla,since1999*/
  white-space: pre-wrap;
  /*Opera4-6*/
  white-space: -o-pre-wrap;
  /*Opera7*/
  word-wrap: break-word;
  /*InternetExplorer5.5+*/
}
</style>