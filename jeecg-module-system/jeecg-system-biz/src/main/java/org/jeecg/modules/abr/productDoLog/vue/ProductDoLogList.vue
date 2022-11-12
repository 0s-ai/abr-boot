<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="执行序号">
              <a-input placeholder="请输入执行序号" v-model="queryParam.doNumber"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="执行编码">
              <a-input placeholder="请输入执行编码" v-model="queryParam.doCode"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="执行类型">
                <j-dict-select-tag placeholder="请选择执行类型" v-model="queryParam.doType" dictCode="do_type"/>
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('执行日志')">导出</a-button>
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

    <product-do-log-modal ref="modalForm" @ok="modalFormOk"></product-do-log-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ProductDoLogModal from './modules/ProductDoLogModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'

  export default {
    name: 'ProductDoLogList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      ProductDoLogModal
    },
    data () {
      return {
        description: '执行日志管理页面',
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
            title:'执行方案',
            align:"center",
            dataIndex: 'prodDoId_dictText'
          },
          {
            title:'执行序号',
            align:"center",
            dataIndex: 'doNumber'
          },
          {
            title:'执行标题',
            align:"center",
            sorter: true,
            dataIndex: 'doTitle'
          },
          {
            title:'执行编码',
            align:"center",
            dataIndex: 'doCode'
          },
          {
            title:'执行依赖',
            align:"center",
            dataIndex: 'doDep'
          },
          {
            title:'执行类型',
            align:"center",
            dataIndex: 'doType_dictText'
          },
          {
            title:'执行人',
            align:"center",
            dataIndex: 'doOwner_dictText'
          },
          {
            title:'执行主机',
            align:"center",
            dataIndex: 'doHost_dictText'
          },
          {
            title:'执行凭据',
            align:"center",
            dataIndex: 'doCert_dictText'
          },
          {
            title:'执行变量',
            align:"center",
            dataIndex: 'doVars'
          },
          {
            title:'执行状态',
            align:"center",
            dataIndex: 'doStatus_dictText'
          },
          {
            title:'开始时间',
            align:"center",
            dataIndex: 'startTime'
          },
          {
            title:'结束时间',
            align:"center",
            dataIndex: 'endTime'
          },
          {
            title:'耗时',
            align:"center",
            dataIndex: 'elapsedTime'
          },
          {
            title:'标准输出',
            align:"center",
            dataIndex: 'stdout'
          },
          {
            title:'输出',
            align:"center",
            dataIndex: 'output'
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
          list: "/productDoLog/productDoLog/list",
          delete: "/productDoLog/productDoLog/delete",
          deleteBatch: "/productDoLog/productDoLog/deleteBatch",
          exportXlsUrl: "/productDoLog/productDoLog/exportXls",
          importExcelUrl: "productDoLog/productDoLog/importExcel",
          
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
        fieldList.push({type:'string',value:'prodDoId',text:'执行方案',dictCode:"product_do where del_flag=0,name,id"})
        fieldList.push({type:'string',value:'doNumber',text:'执行序号',dictCode:''})
        fieldList.push({type:'string',value:'doTitle',text:'执行标题',dictCode:''})
        fieldList.push({type:'string',value:'doCode',text:'执行编码',dictCode:''})
        fieldList.push({type:'Text',value:'doDep',text:'执行依赖',dictCode:''})
        fieldList.push({type:'string',value:'doType',text:'执行类型',dictCode:'do_type'})
        fieldList.push({type:'sel_user',value:'doOwner',text:'执行人'})
        fieldList.push({type:'string',value:'doHost',text:'执行主机',dictCode:"ai_host where del_flag=0,name,id"})
        fieldList.push({type:'string',value:'doCert',text:'执行凭据',dictCode:"ai_cert where del_flag=0,name,id"})
        fieldList.push({type:'Text',value:'doVars',text:'执行变量',dictCode:''})
        fieldList.push({type:'string',value:'doStatus',text:'执行状态',dictCode:'do_status'})
        fieldList.push({type:'datetime',value:'startTime',text:'开始时间'})
        fieldList.push({type:'datetime',value:'endTime',text:'结束时间'})
        fieldList.push({type:'string',value:'elapsedTime',text:'耗时',dictCode:''})
        fieldList.push({type:'Text',value:'stdout',text:'标准输出',dictCode:''})
        fieldList.push({type:'Text',value:'output',text:'输出',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>