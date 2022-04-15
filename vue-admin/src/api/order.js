import service from "../utils/request";

export function FindAllOrder() {
    return service({
        url: '/order',
        method: 'get'
    })
}

export function UpdateOrder(data) {
    return service({
        url: '/order',
        method: 'put',
        data: data
    })
}

export function CreateOrderException(data) {
    return service({
        url: '/oe',
        method: 'post',
        data: data
    })
}

export function HandleOrderException(data) {
    return service({
        url: '/oe',
        method: 'put',
        data: data
    })
}

export function ListOrderException() {
    return service({
        url: '/oe',
        method: 'get'
    })
}

export function FindUserOrder(data) {
    return service({
        url: '/order/user/'+data,
        method: 'get'
    })
}

export function GetOrderStatistics(data) {
    return service({
        url: '/order/statistics/',
        method: 'get',
        data: data
    })
}
