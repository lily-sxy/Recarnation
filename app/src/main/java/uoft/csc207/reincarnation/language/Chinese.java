package uoft.csc207.reincarnation.language;

import java.util.HashMap;

public class Chinese implements Language {
    private HashMap<String, String> translation;

    Chinese() {
        translation = new HashMap<>();

        translation.put("main.start.start", "开始游戏");
        translation.put("main.start.cont", "继续游戏");
        translation.put("main.start.exit", "退出");

        translation.put("main.levels.back", "返回");
        translation.put("main.levels.level1", "第一关");
        translation.put("main.levels.level2", "第二关");
        translation.put("main.levels.level3", "第三关");
        translation.put("main.levels.ending", "结局");

        translation.put("escape.types.0", "一张卡片");
        translation.put("escape.types.1", "一张卡片");
        translation.put("escape.types.2", "一张卡片");
        translation.put("escape.types.3", "一张卡片");
        translation.put("escape.types.4", "一张卡片");
        translation.put("escape.types.5", "一张卡片");
        translation.put("escape.types.6", "一个奶酪");
        translation.put("escape.types.7", "一块冰");
        translation.put("escape.types.8", "一把钥匙");

        translation.put("escape.nget.0", "一个柜子");
        translation.put("escape.nget.1", "一张地毯");
        translation.put("escape.nget.2", "一只老鼠");
        translation.put("escape.nget.3", "一个书架");
        translation.put("escape.nget.4", "一个灶台");

        translation.put("escape.open.0", "打开了柜子的抽屉");
        translation.put("escape.open.1", "掀开了地毯");
        translation.put("escape.open.2", "老鼠开始吃奶酪");
        translation.put("escape.open.3", "用钥匙打开了书架的门");
        translation.put("escape.open.4", "打开了冰箱");
        translation.put("escape.open.5", "冰融化了");


        translation.put("escape.nopen.0", "-");
        translation.put("escape.nopen.1", "-");
        translation.put("escape.nopen.2", "有一只老鼠。");
        translation.put("escape.nopen.3", "锁着的。");
        translation.put("escape.nopen.4", "-");
        translation.put("escape.nopen.5", "有一个灶台。");

        translation.put("escape.start", "你发现自己在一个陌生的房间，也许你应该尝试着逃出去。");
        translation.put("escape.pause", "暂停");
        translation.put("escape.restart", "继续游戏");
        translation.put("escape.exit", "退出游戏");
        translation.put("escape.nothing", "什么都没有发生");
        translation.put("escape.move", "你去了房间 ");
        translation.put("escape.get", "你获得了");
        translation.put("escape.nopen", "");
        translation.put("escape.open", "");
        translation.put("escape.escape", "你试图从这个房间逃出去。");
        translation.put("escape.help", "房间里所有东西都是有毒的，不要碰太多次。\n注意颜色");
        translation.put("escape.resume", "继续游戏");
        translation.put("escape.touch", "触碰次数:");
        translation.put("escape.death", "死亡次数:");
        translation.put("escape.incorrect", "密码错误");

        translation.put("escape.end.escape", "你逃出去了!");
        translation.put("escape.end.time", "\n时间: ");
        translation.put("escape.end.death", "\n死亡次数: ");
        translation.put("escape.end.score", "\n分数: ");
        translation.put("escape.end.continue", "\n点击房间继续...");


        translation.put("maze.pause.seed", "Seed: ");
        translation.put("maze.pause.time", "用时: ");
        translation.put("maze.pause.distance", "移动距离: ");
        translation.put("maze.pause.coin", "硬币: ");
        translation.put("maze.pause.retry", "重试次数: ");
        translation.put("maze.pause.giveup", "放弃");
        translation.put("maze.pause.exit", "退出");

        translation.put("maze.end.win", "你找到了出口!");
        translation.put("maze.end.continue", "点击屏幕继续。");
        translation.put("maze.end.seed", "Seed: ");
        translation.put("maze.end.time", "用时: ");
        translation.put("maze.end.distance", "移动距离: ");
        translation.put("maze.end.coin", "硬币: ");
        translation.put("maze.end.retry", "失败次数: ");
        translation.put("maze.end.score", "分数: ");

        translation.put("maze.hint.title", "帮助与提示");
        translation.put("maze.hint.continue", "点击屏幕继续。");
        translation.put("maze.hint.text0", "拖动屏幕来移动玩家。");
        translation.put("maze.hint.text1", "去寻找出口，逃出这个迷宫。");
        translation.put("maze.hint.text2", "看到硬币了？捡起来，会有用的。");
        translation.put("maze.hint.text3", "这是出口的颜色");
        translation.put("maze.hint.text4", "这是硬币的颜色");
        translation.put(
                "maze.hint.text5",
                "找不到出口的话可以试着点“" +
                        translation.get("maze.pause.giveup") + "”。"
        );
        translation.put(
                "maze.hint.text6",
                "彻底放弃的话点击“" +
                        translation.get("maze.pause.exit") + "”。"
        );

        translation.put("frogger.view.death", "死亡次数:");
        translation.put("frogger.pause.hint1", "提示: 不要让狗注意到你");
        translation.put("frogger.pause.hint2", "也不要跳到乌云上");
        translation.put("frogger.pause.hint3", "金色云彩或许会带来好运");
        translation.put("frogger.pause.resume", "返回");
        translation.put("frogger.pause.exit", "退出");
        translation.put("frogger.level.title", "是否进入下一关？");
        translation.put("frogger.level.yes", "是");
        translation.put("frogger.level.no", "否");
        translation.put("frogger.win.title", "恭喜你过关了！");
        translation.put("frogger.win.time", "时间: ");
        translation.put("frogger.win.death", "死亡次数: ");
        translation.put("frogger.win.score", "最后得分: ");
        translation.put("frogger.win.continuous", "继续");
        translation.put("frogger.lose.restart", "重新开始");
        translation.put("frogger.lose.exit", "退出");


        translation.put("fail", "你被永远困在了这里!");
    }


    /**
     * Get the text correspond to target in the language.
     *
     * @param text_target the target text, format "a.b.c"
     * @return The translated version of text. If text not found, original text will be returned.
     */
    public String getText(String text_target) {
        if (translation.containsKey(text_target))
            return translation.get(text_target);
        else
            return text_target;
    }
}
