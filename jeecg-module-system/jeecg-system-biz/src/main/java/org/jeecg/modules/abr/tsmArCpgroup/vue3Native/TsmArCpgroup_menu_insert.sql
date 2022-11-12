-- 注意：该页面对应的前台目录为views/tsmArCpgroup文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2022102607011030300', NULL, '归档副本组', '/tsmArCpgroup/tsmArCpgroupList', 'tsmArCpgroup/TsmArCpgroupList', NULL, NULL, 0, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2022-10-26 07:01:30', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022102607011040301', '2022102607011030300', '添加归档副本组', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.abr:tsm_ar_cpgroup:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-10-26 07:01:30', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022102607011040302', '2022102607011030300', '编辑归档副本组', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.abr:tsm_ar_cpgroup:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-10-26 07:01:30', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022102607011040303', '2022102607011030300', '删除归档副本组', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.abr:tsm_ar_cpgroup:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-10-26 07:01:30', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022102607011040304', '2022102607011030300', '批量删除归档副本组', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.abr:tsm_ar_cpgroup:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-10-26 07:01:30', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022102607011040305', '2022102607011030300', '导出excel_归档副本组', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.abr:tsm_ar_cpgroup:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-10-26 07:01:30', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022102607011040306', '2022102607011030300', '导入excel_归档副本组', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.abr:tsm_ar_cpgroup:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-10-26 07:01:30', NULL, NULL, 0, 0, '1', 0);