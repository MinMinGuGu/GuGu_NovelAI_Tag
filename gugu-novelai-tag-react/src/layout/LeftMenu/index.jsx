import React, {Component} from "react";
import {Menu} from "antd";
import {PieChartOutlined, ProfileOutlined} from "@ant-design/icons";
import {withRouter} from "react-router-dom";
import {get} from "../../utils/requestUtil";
import apis from "../../config/setting";

class LeftMenu extends Component {

    componentWillMount() {
        this.setState({selectedKeys: [this.props.history.location.pathname ?? '/']});
        this.testRearEnd();
    }

    componentDidMount() {
        this.props.history.listen(location => {
            this.setState({selectedKeys: [location.pathname]});
            this.testRearEnd();
        });
    }

    state = {
        rearEnd: false,
        selectedKeys: [],
    }

    testRearEnd = () => {
        get(apis.indexApi + '/test').then(json => {
            this.setState({rearEnd: true});
        }).catch(error => {
            this.setState({rearEnd: false});
            console.error('后端菜单已禁用');
        });
    };

    onClick = (menuItem) => {
        this.props.history.push(menuItem.key);
    }

    getItem = (label, key, icon, children, type) => {
        return {
            key,
            icon,
            children,
            label,
            type,
        };
    }

    render() {
        const {rearEnd, selectedKeys} = this.state;
        let rearMenu = this.getItem('管理', "/manage", <ProfileOutlined/>, [
            this.getItem('元素管理', "/manage/attribute"),
            this.getItem('分类管理', "/manage/category"),
            this.getItem('配置管理', "/manage/system"),
        ]);
        if (!rearEnd) {
            let children = rearMenu.children;
            for (const child of children) {
                child.disabled = true;
            }
            rearMenu.disabled = true;
        }
        const menuList = [
            this.getItem('首页', "/", <PieChartOutlined/>),
            rearMenu,
        ]
        return (
            <Menu
                defaultOpenKeys={['/manage']}
                selectedKeys={selectedKeys}
                mode="inline"
                theme="dark"
                items={menuList}
                onClick={this.onClick}
            >
            </Menu>
        );
    }
}

export default withRouter(LeftMenu);
