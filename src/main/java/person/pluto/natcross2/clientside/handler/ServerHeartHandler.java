package person.pluto.natcross2.clientside.handler;

import lombok.extern.slf4j.Slf4j;
import person.pluto.natcross2.clientside.adapter.IClientAdapter;
import person.pluto.natcross2.model.InteractiveModel;
import person.pluto.natcross2.model.enumeration.InteractiveTypeEnum;
import person.pluto.natcross2.model.enumeration.NatcrossResultEnum;

/**
 * <p>
 * 心跳检测
 * </p>
 *
 * @author Pluto
 * @since 2020-04-15 13:02:09
 */
@Slf4j
public class ServerHeartHandler implements IClientHandler<InteractiveModel, InteractiveModel> {

    public static final ServerHeartHandler INSTANCE = new ServerHeartHandler();

    @Override
    public boolean proc(InteractiveModel model,
                        IClientAdapter<? extends InteractiveModel, ? super InteractiveModel> clientAdapter) throws Exception {
        InteractiveTypeEnum interactiveTypeEnum = InteractiveTypeEnum.getEnumByName(model.getInteractiveType());
        if (InteractiveTypeEnum.HEART_TEST.equals(interactiveTypeEnum)) {
            InteractiveModel sendModel = InteractiveModel.of(model.getInteractiveSeq(), InteractiveTypeEnum.HEART_TEST_REPLY,
                    NatcrossResultEnum.SUCCESS.toResultModel());
            clientAdapter.getSocketChannel().writeAndFlush(sendModel);

            return true;
        } else if (InteractiveTypeEnum.HEART_TEST_REPLY.equals(interactiveTypeEnum)) {
            log.debug("HEART_TEST_REPLY ignore");
            return true;
        }

        return false;
    }

}
