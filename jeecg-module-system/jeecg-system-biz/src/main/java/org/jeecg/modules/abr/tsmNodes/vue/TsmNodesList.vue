<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="服务器">
              <j-dict-select-tag placeholder="请选择服务器" v-model="queryParam.tsmHostId" dictCode="ai_host where del_flag=0,name,id"/>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="名称">
              <a-input placeholder="请输入名称" v-model="queryParam.name"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="策略域">
                <j-dict-select-tag placeholder="请选择策略域" v-model="queryParam.domainName" dictCode="tsm_domains where del_flag=0,name,id"/>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="主机名">
                <a-input placeholder="请输入主机名" v-model="queryParam.tcpName"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="IP">
                <a-input placeholder="请输入IP" v-model="queryParam.tcpAddress"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="平台类型">
                <a-input placeholder="请输入平台类型" v-model="queryParam.osType"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="操作系统版本">
                <a-input placeholder="请输入操作系统版本" v-model="queryParam.osVersion"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="tsm版本">
                <a-input placeholder="请输入tsm版本" v-model="queryParam.tsmVersion"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('客户机')">导出</a-button>
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

    <tsm-nodes-modal ref="modalForm" @ok="modalFormOk"></tsm-nodes-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import TsmNodesModal from './modules/TsmNodesModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'

  export default {
    name: 'TsmNodesList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      TsmNodesModal
    },
    data () {
      return {
        description: '客户机管理页面',
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
            title:'服务器',
            align:"center",
            dataIndex: 'tsmHostId_dictText'
          },
          {
            title:'名称',
            align:"center",
            dataIndex: 'name'
          },
          {
            title:'注册时间',
            align:"center",
            dataIndex: 'regTime'
          },
          {
            title:'授予删除归档权限',
            align:"center",
            dataIndex: 'archdelete'
          },
          {
            title:'同步标记',
            align:"center",
            dataIndex: 'syncFlag'
          },
          {
            title:'软删除标记',
            align:"center",
            dataIndex: 'delFlag'
          },
          {
            title:'授予删除备份权限',
            align:"center",
            dataIndex: 'backdelete'
          },
          {
            title:'密码过期天数',
            align:"center",
            dataIndex: 'passexp'
          },
          {
            title:'策略域',
            align:"center",
            dataIndex: 'domainName_dictText'
          },
          {
            title:'主机名',
            align:"center",
            dataIndex: 'tcpName'
          },
          {
            title:'IP',
            align:"center",
            dataIndex: 'tcpAddress'
          },
          {
            title:'平台类型',
            align:"center",
            dataIndex: 'osType'
          },
          {
            title:'操作系统版本',
            align:"center",
            dataIndex: 'osVersion'
          },
          {
            title:'tsm版本',
            align:"center",
            dataIndex: 'tsmVersion'
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
          list: "/tsmNodes/tsmNodes/list",
          delete: "/tsmNodes/tsmNodes/delete",
          deleteBatch: "/tsmNodes/tsmNodes/deleteBatch",
          exportXlsUrl: "/tsmNodes/tsmNodes/exportXls",
          importExcelUrl: "tsmNodes/tsmNodes/importExcel",
          
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
        fieldList.push({type:'string',value:'tsmHostId',text:'服务器',dictCode:"ai_host where del_flag=0,name,id"})
        fieldList.push({type:'string',value:'name',text:'名称',dictCode:''})
        fieldList.push({type:'string',value:'regTime',text:'注册时间',dictCode:''})
        fieldList.push({type:'string',value:'archdelete',text:'授予删除归档权限',dictCode:''})
        fieldList.push({type:'string',value:'syncFlag',text:'同步标记',dictCode:''})
        fieldList.push({type:'string',value:'delFlag',text:'软删除标记',dictCode:''})
        fieldList.push({type:'string',value:'password',text:'密码',dictCode:''})
        fieldList.push({type:'string',value:'backdelete',text:'授予删除备份权限',dictCode:''})
        fieldList.push({type:'string',value:'passexp',text:'密码过期天数',dictCode:''})
        fieldList.push({type:'string',value:'maxnummp',text:'可用挂载点数量',dictCode:''})
        fieldList.push({type:'string',value:'domainName',text:'策略域',dictCode:"tsm_domains where del_flag=0,name,id"})
        fieldList.push({type:'string',value:'tcpName',text:'主机名',dictCode:''})
        fieldList.push({type:'string',value:'tcpAddress',text:'IP',dictCode:''})
        fieldList.push({type:'string',value:'osType',text:'平台类型',dictCode:''})
        fieldList.push({type:'string',value:'osVersion',text:'操作系统版本',dictCode:''})
        fieldList.push({type:'string',value:'tsmVersion',text:'tsm版本',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>