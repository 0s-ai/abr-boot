<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('主机文件')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text,record">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" :preview="record.id" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <ai-host-file-modal ref="modalForm" @ok="modalFormOk"></ai-host-file-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import AiHostFileModal from './modules/AiHostFileModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'

  export default {
    name: 'AiHostFileList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      AiHostFileModal
    },
    data () {
      return {
        description: '主机文件管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'标题',
            align:"center",
            dataIndex: 'title'
          },
          {
            title:'标识',
            align:"center",
            dataIndex: 'tag'
          },
          {
            title:'主机',
            align:"center",
            dataIndex: 'hostId_dictText'
          },
          {
            title:'密钥',
            align:"center",
            dataIndex: 'certId_dictText'
          },
          {
            title:'文件绝对路径',
            align:"center",
            dataIndex: 'path'
          },
          {
            title:'内容',
            align:"center",
            dataIndex: 'content'
          },
          {
            title:'版本',
            align:"center",
            dataIndex: 'version'
          },
          {
            title:'Hash值',
            align:"center",
            dataIndex: 'gitUuid'
          },
          {
            title:'文件状态',
            align:"center",
            dataIndex: 'fileStatus'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/aiHostFile/aiHostFile/list",
          delete: "/aiHostFile/aiHostFile/delete",
          deleteBatch: "/aiHostFile/aiHostFile/deleteBatch",
          exportXlsUrl: "/aiHostFile/aiHostFile/exportXls",
          importExcelUrl: "aiHostFile/aiHostFile/importExcel",
          
        },
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'title',text:'标题',dictCode:''})
        fieldList.push({type:'string',value:'tag',text:'标识',dictCode:''})
        fieldList.push({type:'string',value:'hostId',text:'主机',dictCode:"ai_host,name,id"})
        fieldList.push({type:'string',value:'certId',text:'密钥',dictCode:"ai_cert,name,id"})
        fieldList.push({type:'string',value:'path',text:'文件绝对路径',dictCode:''})
        fieldList.push({type:'Text',value:'content',text:'内容',dictCode:''})
        fieldList.push({type:'string',value:'version',text:'版本',dictCode:''})
        fieldList.push({type:'string',value:'gitUuid',text:'Hash值',dictCode:''})
        fieldList.push({type:'string',value:'fileStatus',text:'文件状态',dictCode:'file_status'})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>