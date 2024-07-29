import request from '@/utils/request'

// 查询内推管理列表
export function listInternalreferral(query) {
  return request({
    url: '/internalreferral/list',
    method: 'get',
    params: query
  })
}

// 查询内推管理详细
export function getInternalreferral(id) {
  return request({
    url: '/internalreferral/' + id,
    method: 'get'
  })
}

// 新增内推管理
export function addInternalreferral(data) {
  return request({
    url: '/internalreferral',
    method: 'post',
    data: data
  })
}

// 修改内推管理
export function updateInternalreferral(data) {
  return request({
    url: '/internalreferral',
    method: 'put',
    data: data
  })
}

// 删除内推管理
export function delInternalreferral(id) {
  return request({
    url: '/internalreferral/internalreferral/' + id,
    method: 'delete'
  })
}
