import Home from '../page/Home';
import Attribute from '../page/Manage/Attribute'
import Category from '../page/Manage/Category'
import System from '../page/Manage/System'

const routers = [
    {
        path: '/',
        component: Home,
        exact: true,
        title: "首页"
    },
    {
        path: '/manage/category',
        component: Category,
        exact: true,
        title: "分类管理"
    },
    {
        path: '/manage/attribute',
        component: Attribute,
        exact: true,
        title: "属性管理"
    },
    {
        path: '/manage/system',
        component: System,
        exact: true,
        title: "系统管理"
    },
]

export default routers